package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Hospedes")
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private String telefone;

    private Cidade cidade;

    private Usuario usuario;
 }
