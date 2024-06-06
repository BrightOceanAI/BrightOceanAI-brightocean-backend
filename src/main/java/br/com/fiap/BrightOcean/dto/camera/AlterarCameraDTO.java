package br.com.fiap.BrightOcean.dto.camera;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record AlterarCameraDTO(
        @Size(min = 3)
        String modeloCamera,
        @Size(min = 3, max = 100)
        String localizacao,
        Float latitude,
        Float longitude
) {

}
