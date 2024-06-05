package br.com.fiap.BrightOcean.dto.diagnostico;

import br.com.fiap.BrightOcean.model.enums.Saude;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CriarDiagnosticoDTO(

        @NotNull
        Saude saude,
        @NotNull
        @PastOrPresent
        LocalDate dataDiagnostico,
        @NotNull
        Long idFotografia,
        @NotNull
        Long idRecife
) {
}
