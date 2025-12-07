package br.ifs.edu.cads.api.hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTO (

        String sigla,

        @NotBlank(message = "O nome do estado é obrigatório!")
        @Size(max = 150, message = "O nome da cidade não pode exceder 150 caracteres!")
        String nome
){}
