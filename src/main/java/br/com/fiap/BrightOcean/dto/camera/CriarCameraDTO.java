package br.com.fiap.BrightOcean.dto.camera;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarCameraDTO(

        @NotNull
        @NotBlank
        @Min(3)
        String modeloCamera,
        @NotNull
        @NotBlank
        @Max(100)
        String localizacao,
        @NotNull
        Float latitude,
        @NotNull
        Float longitude
) {
}
