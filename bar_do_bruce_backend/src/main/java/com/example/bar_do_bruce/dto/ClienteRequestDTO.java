package com.example.bar_do_bruce.dto;

public class ClienteRequestDTO {
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;
}
