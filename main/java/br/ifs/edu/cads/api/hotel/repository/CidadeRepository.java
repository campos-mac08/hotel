package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Optional<Cidade> findByNome(String nome);

    Optional<Cidade> findByEstado(Estado estado);

    boolean existsByNomeAndEstado(String nome, Estado estado);

}
