package br.com.fiap.BrightOcean.dto.diagnostico;

import br.com.fiap.BrightOcean.model.Diagnostico;
import br.com.fiap.BrightOcean.model.enums.Saude;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DetalharDiagnosticoDTO(
        Long idDiagnostico,
        Saude saude,
        LocalDate dataDiagnostico,
        Long idFotografia,

        Long idRecife
) {
    public DetalharDiagnosticoDTO(Diagnostico diagnostico) {
        this(diagnostico.getIdDiagnostico(), diagnostico.getSaude(), diagnostico.getDataDiagnostico(), diagnostico.getFotografia().getIdFotografia(), diagnostico.getRecife().getIdRecife());
    }
}
