package br.com.carlos.Simple_E_Commerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(@Email @NotBlank String email, String password, String role) {
}
