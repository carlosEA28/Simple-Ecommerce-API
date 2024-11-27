package br.com.carlos.Simple_E_Commerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CustomerResetPasswordDto(@NotBlank String token, @NotBlank @Min(4) String password) {
}
