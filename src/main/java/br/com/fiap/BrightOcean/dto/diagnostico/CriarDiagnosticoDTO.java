package br.com.fiap.BrightOcean.dto.diagnostico;

import br.com.fiap.BrightOcean.model.enums.Saude;

import java.time.LocalDate;

public record CriarDiagnosticoDTO(
        Saude saude,
        LocalDate dataDiagnostico,
        Long idFotografia,
        Long idRecife
) {
}
