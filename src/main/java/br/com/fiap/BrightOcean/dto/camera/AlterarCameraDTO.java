package br.com.fiap.BrightOcean.dto.camera;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record AlterarCameraDTO(
        @Min(3)
        String modeloCamera,
        @Max(100)
        String localizacao,
        Float latitude,
        Float longitude
) {

}
