package br.com.fiap.BrightOcean.dto.camera;

import jakarta.validation.constraints.*;

public record CriarCameraDTO(

        @NotNull
        @NotBlank
        @Size(min = 3, max = 100)
        String modeloCamera,
        @NotNull
        @NotBlank
        @Size(min = 3, max = 100)
        String localizacao,
        @NotNull
        Float latitude,
        @NotNull
        Float longitude
) {
}
