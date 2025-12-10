package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enumeration.FormaPagamento;
import br.ifs.edu.cads.api.hotel.enumeration.StatusReserva;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CategoriaQuarto categoria;

    private Quarto quarto;

    private Hospede hospede;

    private Hospede[] convidados;

    private Funcionario funcionario;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private LocalDate dataCheckin;

    private LocalDate dataCheckout;

    private float valorDiaria;

    private FormaPagamento pagamento;

    private StatusReserva reserva;
}
