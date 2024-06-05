package br.com.fiap.BrightOcean.dto.recife;

import br.com.fiap.BrightOcean.model.enums.Classificacao;


public record CriarRecifeDTO(
        String nome,
        Classificacao classificacao,
        float latitude,
        float longitude,
        boolean emRestauracao,
        String estratgiaRestauracao
) {
}
