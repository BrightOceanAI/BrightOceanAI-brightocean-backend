package br.com.fiap.BrightOcean.dto.diagnostico;

import br.com.fiap.BrightOcean.model.enums.Saude;

import java.time.LocalDate;

public record AlterarDiagnosticoDTO(
        Saude saude
) {
}
