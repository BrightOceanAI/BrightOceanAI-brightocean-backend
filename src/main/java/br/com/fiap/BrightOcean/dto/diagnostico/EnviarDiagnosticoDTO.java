package br.com.fiap.BrightOcean.dto.diagnostico;

import jakarta.validation.constraints.NotNull;


public record EnviarDiagnosticoDTO(
        @NotNull
        Long idFotografia,
        @NotNull
        Long idRecife
) {
}
