package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enumeration.Cargo;
import jakarta.persistence.*;

@Entity
@Table(name = "Funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private Cargo cargo;
}
