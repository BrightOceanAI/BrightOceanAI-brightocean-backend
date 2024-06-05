package br.com.fiap.BrightOcean.dto.usuario;

import br.com.fiap.BrightOcean.model.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record AtualizacaoUsuarioDTO(
        @Min(3)
        String nome,
        @Email(message = "Insira um e-mail válido")
        String email,
        @Size(min = 4, max = 20)
        String senha,
        Perfil perfil
) {
}
