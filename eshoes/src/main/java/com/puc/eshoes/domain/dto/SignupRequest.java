package com.puc.eshoes.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,

        @Email(message = "Formato de email inválido")
        @NotBlank(message = "Email não pode ser vazio")
        String email,

        @NotBlank(message = "Tipo não pode ser vazio")
        String tipo,
        @NotBlank(message = "Senha não pode ser vazio")
        @Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
        String senha) {
}
