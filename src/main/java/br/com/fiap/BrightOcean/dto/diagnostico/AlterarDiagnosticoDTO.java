package br.com.fiap.BrightOcean.dto.diagnostico;

import br.com.fiap.BrightOcean.model.enums.Saude;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AlterarDiagnosticoDTO(
        @NotNull
        Saude saude
) {
}
