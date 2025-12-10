package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enumeration.StatusQuarto;
import jakarta.persistence.*;

@Entity
@Table(name = "Quartos")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numBloco;

    private int numAndar;

    private CategoriaQuarto categoria;

    private StatusQuarto status;
}
