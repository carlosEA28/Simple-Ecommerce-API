package br.com.carlos.Simple_E_Commerce.dto;

import jakarta.validation.constraints.Email;

public record CustomerReedemPasswordDto(@Email String email) {
}
