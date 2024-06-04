package br.com.fiap.BrightOcean.dto.camera;

public record CriarCameraDTO(
        String modeloCamera,
        String localizacao,
        float latitude,
        float longitude
) {
}
