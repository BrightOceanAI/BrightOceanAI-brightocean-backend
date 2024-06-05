package br.com.fiap.BrightOcean.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroLoginDTO(
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String senha
) {
}