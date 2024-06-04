package br.com.fiap.BrightOcean.dto.camera;

public record AlterarCameraDTO(
        String modeloCamera,
        String localizacao,
        Float latitude,
        Float longitude
) {

}
