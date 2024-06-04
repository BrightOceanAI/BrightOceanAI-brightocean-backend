package br.com.fiap.BrightOcean.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Fotografia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFotografia;
    private String link;
    private LocalDateTime dataHoraFoto;

    @OneToMany
    private Camera camera;
}
