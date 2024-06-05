package br.com.fiap.BrightOcean.model;

import br.com.fiap.BrightOcean.dto.fotografia.AlterarFotografiaDTO;
import br.com.fiap.BrightOcean.dto.fotografia.CriarFotografiaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fotografia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFotografia;
    private String link;
    private LocalDateTime dataHoraFoto;

    @ManyToOne
    private Camera camera;

    public Fotografia(String link, Camera camera) {
        this.link = link;
        this.dataHoraFoto = LocalDateTime.now();
        this.camera = camera;
    }

    public void atualizarFotografia(AlterarFotografiaDTO fotografiaDTO) {
        if(fotografiaDTO.link() != null) this.link = fotografiaDTO.link();
    }
}
