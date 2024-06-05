package br.com.fiap.BrightOcean.dto.usuario;

import br.com.fiap.BrightOcean.model.enums.Perfil;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

public record CadastroUsuarioDTO(

        @NotBlank
        @NotNull
        @Min(3)
        String nome,
        @NotBlank
        @NotNull
        @Min(value = 11, message = "Insira um cpf válido")
        String cpf,
        @NotBlank
        @NotNull
        @Email(message = "Insira um e-mail válido")
        String email,
        @NotBlank
        @NotNull
        @Size(min = 4, max = 20)
        String senha,
        @NotNull
        Perfil perfil
) {
}
