package br.ifs.edu.cads.api.hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTO (

        @Size(min = 2, max = 2, message = "A sigla do estado não pode ser maior que 2 caracteres!")
        String sigla,

        @NotBlank(message = "O nome do estado é obrigatório!")
        @Size(max = 150, message = "O nome do estado não pode exceder 150 caracteres!")
        String nome
){}
