package br.com.fiap.BrightOcean.dto.fotografia;


import java.time.LocalDateTime;

public record CriarFotografiaDTO(
     String link,
     LocalDateTime dataHoraFoto,
     Long idCamera
) {
}
