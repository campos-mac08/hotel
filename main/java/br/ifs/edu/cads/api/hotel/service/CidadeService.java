package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.CidadeDTO;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.exception.RecursoNaoEncontradoException;
import br.ifs.edu.cads.api.hotel.exception.RegraDeNegocioException;
import br.ifs.edu.cads.api.hotel.repository.CidadeRepository;
import br.ifs.edu.cads.api.hotel.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public CidadeDTO toDTO(Cidade cidade){
        String sigla = cidade.getEstado() != null ? cidade.getEstado().getSigla() : null;

        return new CidadeDTO(cidade.getId(), cidade.getNome(), sigla);
    }
    public CidadeDTO buscarPorId(Long id){
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cidade não encontrada: " + id));

        return toDTO(cidade);
    }
    public CidadeDTO buscarPorNome(String nome){
        Cidade cidade = cidadeRepository.findByNome(nome)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cidade não encontrada: " + nome));

        return toDTO(cidade);
    }
    public List<CidadeDTO> listarTodos(){
        return cidadeRepository.findAll().stream().map(this::toDTO).toList();
    }
    public List<CidadeDTO> listarPorEstado(String sigla){
        Estado estado = estadoRepository.findById(sigla)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estado não encontrado com ID: " + sigla));

        return cidadeRepository.findByEstado(estado)
                .stream()
                .map(this::toDTO)
                .toList();
    }
    private Cidade fromDTO(CidadeDTO cidadeDTO) {
        Cidade cidade = new Cidade();
        cidade.setId(cidadeDTO.id());
        cidade.setNome(cidadeDTO.nome());

        if (cidadeDTO.sigla() != null) {
           Estado estado = estadoRepository.findById(cidadeDTO.sigla())
                   .orElseThrow(() -> new RecursoNaoEncontradoException("Estado não encontrado com ID: " + cidadeDTO.sigla()));

           cidade.setEstado(estado);
        }
        return cidade;
    }
    @Transactional
    public CidadeDTO criar(CidadeDTO cidadeDTO){
        if (cidadeDTO.id() != null && cidadeRepository.existsById(cidadeDTO.id())){
            throw new RegraDeNegocioException("Id já existe: " + cidadeDTO.id());
        }
        Estado estado = estadoRepository.findById(cidadeDTO.sigla())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estado não encontrado com ID: " + cidadeDTO.sigla()));

        if (cidadeRepository.existsByNomeAndEstado(cidadeDTO.nome(), estado)){
            throw new RegraDeNegocioException("Cidade já existe: " + cidadeDTO.nome()+ "/" + cidadeDTO.sigla());
        }
        Cidade cidade = fromDTO(cidadeDTO);
        cidade.setEstado(estado);
        cidadeRepository.save(cidade);
        return toDTO(cidade);
    }
    @Transactional
    public CidadeDTO atualizar(Long id, CidadeDTO cidadeDTO){
        Cidade cidadeAtual = cidadeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cidade não encontrada com ID: " + id));

        Cidade cidade = fromDTO(cidadeDTO);
        if (cidade.getId() != null && !cidadeRepository.existsById(cidade.getId())){
            cidadeAtual.setId(cidade.getId());
        }
        if (cidade.getNome() != null && !cidade.getNome().isBlank()){
            cidadeAtual.setNome(cidade.getNome());
        }
        if (cidade.getEstado() != null){
            cidadeAtual.setEstado(cidade.getEstado());
        }
        cidadeRepository.save(cidadeAtual);
        return toDTO(cidadeAtual);
    }
    @Transactional
    public void deletar(Long id){
        if (!cidadeRepository.existsById(id)){
            throw new RegraDeNegocioException("Id não existe: " + id);
        }
        cidadeRepository.deleteById(id);
    }

}
