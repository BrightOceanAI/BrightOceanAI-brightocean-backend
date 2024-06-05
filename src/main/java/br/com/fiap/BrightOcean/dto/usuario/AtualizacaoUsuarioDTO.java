package br.com.fiap.BrightOcean.dto.usuario;

import br.com.fiap.BrightOcean.model.enums.Perfil;

public record AtualizacaoUsuarioDTO(
        String nome,
        String email,
        String senha,
        Perfil perfil
) {
}
