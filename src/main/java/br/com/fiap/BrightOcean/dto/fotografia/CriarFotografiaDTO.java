package br.com.fiap.BrightOcean.dto.fotografia;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record CriarFotografiaDTO(
        @NotNull
        @NotBlank
        String link,

        @NotNull
        @PastOrPresent
        LocalDateTime dataHoraFoto,

        @NotNull
        Long idCamera
) {
}
