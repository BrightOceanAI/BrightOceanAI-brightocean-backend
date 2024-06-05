package br.com.fiap.BrightOcean.dto.fotografia;

import br.com.fiap.BrightOcean.model.Fotografia;


import java.time.LocalDateTime;

public record DetalharFotografiaDTO(

         Long idFotografia,
         String link,
         LocalDateTime dataHoraFoto,
         Long idCamera
) {
    public DetalharFotografiaDTO(Fotografia fotografia) {
        this(fotografia.getIdFotografia(), fotografia.getLink(), fotografia.getDataHoraFoto(), fotografia.getCamera().getIdCamera());
    }
}
