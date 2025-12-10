package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "Cancelamentos")
public class Cancelamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Motivo", nullable = false, length = 200)
    private String motivo;

    @Column(name = "Data", nullable = false)
    private LocalDate dataCancelamento;

    @OneToOne(mappedBy = "reserva")
    private Reserva reserva;

}
