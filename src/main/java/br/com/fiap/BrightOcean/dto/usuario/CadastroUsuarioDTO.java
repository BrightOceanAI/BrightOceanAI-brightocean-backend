package br.com.fiap.BrightOcean.dto.usuario;

import br.com.fiap.BrightOcean.model.enums.Perfil;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record CadastroUsuarioDTO(
        String nome,
        String cpf,
        String email,
        String senha,
        Perfil perfil
) {
}
