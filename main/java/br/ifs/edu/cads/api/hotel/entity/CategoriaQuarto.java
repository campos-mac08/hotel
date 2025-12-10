package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enumeration.Posicao;
import jakarta.persistence.*;

@Entity
@Table(name = "Categorias")
public class CategoriaQuarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private int max_hospedes;

    private Quarto[] quartos;

    private float valorDiaria;

    private Posicao posicao;

    private Comodidades comodidade;


}
