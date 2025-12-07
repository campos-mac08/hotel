package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.EstadoDTO;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }
    private EstadoDTO toDTO(Estado estado){

        return new EstadoDTO(estado.getNome(), estado.getSigla());
    }
    public EstadoDTO buscarPorSigla(String sigla){
        Optional<Estado> estado = estadoRepository.findById(sigla);

        EstadoDTO estadoDTO = null;
        if (estado.get() != null){
            estadoDTO = toDTO(estado.get());
        }
        return estadoDTO;
    }
    public EstadoDTO buscarPorNome(String nome){
        Optional<Estado> estado = estadoRepository.findByNome(nome);

        EstadoDTO estadoDTO = null;
        if (estado.get() != null){
            estadoDTO = toDTO(estado.get());
        }
        return estadoDTO;
    }
    public List<EstadoDTO> listar(){
        return estadoRepository.findAll().stream().map(this::toDTO).toList();
    }
    public EstadoDTO buscarPorId(String sigla){
        Optional<Estado> estado = estadoRepository.findById(sigla);

        EstadoDTO estadoDTO = null;
        if (estado.get() != null){
            estadoDTO = toDTO(estado.get());
        }
        return estadoDTO;
    }
    private Estado fromDTO(EstadoDTO estadoDTO){
        return new Estado(estadoDTO.nome(), estadoDTO.sigla());
    }
    @Transactional
    public EstadoDTO salvar(EstadoDTO estadoDTO){
        String sigla = estadoDTO.sigla();
        if (estadoRepository.existsById(sigla)){
            throw new RegraDeNegocioException("Sigla já existe: " + sigla);
        }

        Estado estado = fromDTO(estadoDTO);
        Estado estadoSalvo = estadoRepository.save(estado);
        return toDTO(estadoSalvo);
    }
    @Transactional
    public EstadoDTO atualizar(String sigla, EstadoDTO estadoDTO){
        if (estadoRepository.existsById(sigla) || estadoRepository.existByNome(estadoDTO.nome())){
            throw new RegraDeNegocioException("Sigla ou nome já existe: " + sigla + "/" +estadoDTO.nome());
        }
        Estado estado = fromDTO(estadoDTO);
        estado.setSigla(sigla);
        return toDTO(estado);
    }
    @Transactional
    public void delete(String sigla){
        if (!estadoRepository.existsById(sigla)){
            throw new RegraDeNegocioException("Sigla não existe: " + sigla);
        }
        estadoRepository.deleteById(sigla);
    }
}
