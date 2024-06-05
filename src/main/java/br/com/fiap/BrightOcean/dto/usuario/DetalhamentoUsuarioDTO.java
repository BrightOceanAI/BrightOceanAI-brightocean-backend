package br.com.fiap.BrightOcean.dto.usuario;

import br.com.fiap.BrightOcean.model.Usuario;
import br.com.fiap.BrightOcean.model.enums.Perfil;

public record DetalhamentoUsuarioDTO(
        Long idUsuario,
        String nome,
        String cpf,
        String email,
        String senha,
        Perfil perfil
) {

    public DetalhamentoUsuarioDTO(Usuario usuario) {
        this(usuario.getIdUsuario(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfil());
    }
}
