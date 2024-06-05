package br.com.fiap.BrightOcean.dto.recife;

import br.com.fiap.BrightOcean.model.enums.Classificacao;
import jakarta.validation.constraints.*;


public record CriarRecifeDTO(
        @NotBlank
        @NotNull
        String nome,
        @NotNull
        Classificacao classificacao,
        @NotNull
        Float latitude,
        @NotNull
        Float longitude,
        @NotNull
        boolean emRestauracao,
        @Size(min = 4, max = 60)
        String estratgiaRestauracao
) {
}
