package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.EstadoDTO;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.exception.RecursoNaoEncontradoException;
import br.ifs.edu.cads.api.hotel.exception.RegraDeNegocioException;
import br.ifs.edu.cads.api.hotel.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }
    private EstadoDTO toDTO(Estado estado){

        return new EstadoDTO(estado.getNome(), estado.getSigla());
    }
    public EstadoDTO buscarPorNome(String nome){
        Estado estado = estadoRepository.findByNome(nome)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estado não encontrado: " + nome));

        return toDTO(estado);
    }
    public List<EstadoDTO> listarTodos(){
        return estadoRepository.findAll().stream().map(this::toDTO).toList();
    }
    public EstadoDTO buscarPorId(String sigla){
        Estado estado = estadoRepository.findById(sigla)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estado não encontrado: " + sigla));

        return toDTO(estado);
    }
    private Estado fromDTO(EstadoDTO estadoDTO){
        return new Estado(estadoDTO.nome(), estadoDTO.sigla());
    }
    @Transactional
    public EstadoDTO criar(EstadoDTO estadoDTO){
        if (estadoRepository.existsById(estadoDTO.sigla())){
            throw new RegraDeNegocioException("Sigla já existe: " + estadoDTO.sigla());
        }
        if (estadoRepository.existByNome(estadoDTO.nome())){
            throw new RegraDeNegocioException("Estado já existe: " + estadoDTO.nome());
        }
        Estado estado = fromDTO(estadoDTO);
        Estado estadoSalvo = estadoRepository.save(estado);
        return toDTO(estadoSalvo);
    }
    @Transactional
    public EstadoDTO atualizar(String sigla, EstadoDTO estadoDTO){
        Estado estadoAtual = estadoRepository.findById(sigla)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estado não encontrada: " + sigla));

        Estado estado = fromDTO(estadoDTO);
        if (estado.getNome() != null && !estado.getNome().isBlank()) {
            estadoAtual.setNome(estado.getNome());
        }
        if (estado.getSigla() != null && !estado.getSigla().isBlank()) {
            estadoAtual.setSigla(estado.getSigla());
        }
        return toDTO(estadoAtual);
    }
    @Transactional
    public void deletar(String sigla){
        if (!estadoRepository.existsById(sigla)){
            throw new RegraDeNegocioException("Sigla não existe: " + sigla);
        }
        estadoRepository.deleteById(sigla);
    }
}
