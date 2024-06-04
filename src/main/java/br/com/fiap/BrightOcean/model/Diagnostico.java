package br.com.fiap.BrightOcean.model;

import br.com.fiap.BrightOcean.model.enums.Saude;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDiagnostico;
    private Saude saude;
    private LocalDateTime dataDiagnostico;

    @OneToOne
    private Fotografia fotografia;
}
