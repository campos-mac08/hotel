package br.ifs.edu.cads.api.hotel.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CidadeDTO(

    Long id,

    @NotBlank(message = "O nome da cidade é obrigatório!")
    @Size(max = 150, message = "O nome da cidade não pode exceder 150 caracteres!")
    String nome,

    String sigla
    ){}
