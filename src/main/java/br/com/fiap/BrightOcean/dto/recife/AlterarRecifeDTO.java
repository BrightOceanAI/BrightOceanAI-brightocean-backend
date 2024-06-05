package br.com.fiap.BrightOcean.dto.recife;

import br.com.fiap.BrightOcean.model.enums.Classificacao;
import jakarta.validation.constraints.*;

public record AlterarRecifeDTO(

        String nome,
        Classificacao classificacao,
        Float latitude,
        Float longitude,
        Boolean emRestauracao,
        @Size(min = 4, max = 60)
        String estratgiaRestauracao
) {
}
