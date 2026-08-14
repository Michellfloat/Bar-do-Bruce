package com.example.bar_do_bruce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRequestDTO {
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @Email(message = "O campo email deve ser um endereço de email válido")
    private String email;

    @NotBlank(message = "O campo telefone é obrigatório")
    @Size(min = 15, max = 15, message = "O telefone deve seguir o formato (XX) XXXXX-XXXX")
    private String telefone;
}
