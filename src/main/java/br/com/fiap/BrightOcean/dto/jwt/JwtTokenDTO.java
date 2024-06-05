package br.com.fiap.BrightOcean.dto.jwt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JwtTokenDTO(
        @NotNull
                @NotBlank
        String token
) {}
