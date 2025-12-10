package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enumeration.PapelUsuario;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private PapelUsuario papel;

    private boolean ativo;
}
