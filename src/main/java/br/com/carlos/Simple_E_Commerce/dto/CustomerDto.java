package br.com.carlos.Simple_E_Commerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerDto(
        @NotBlank(message = "O nome não pode estar vazio.")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
        String name,

        @NotBlank(message = "O endereço não pode estar vazio.")
        String address,

        @NotBlank(message = "O e-mail não pode estar vazio.")
        @Email(message = "O e-mail deve ser um endereço válido.")
        String email,

        @NotBlank(message = "A senha não pode estar vazia.")
        @Size(min = 5, max = 20, message = "A senha deve ter entre 5 e 20 caracteres.")
        String password
) {
}
