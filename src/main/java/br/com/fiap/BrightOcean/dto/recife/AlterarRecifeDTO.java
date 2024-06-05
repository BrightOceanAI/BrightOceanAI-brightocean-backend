package br.com.fiap.BrightOcean.dto.recife;

import br.com.fiap.BrightOcean.model.enums.Classificacao;

public record AlterarRecifeDTO(
        String nome,
        Classificacao classificacao,
        Float latitude,
        Float longitude,
        Boolean emRestauracao,
        String estratgiaRestauracao
) {
}
