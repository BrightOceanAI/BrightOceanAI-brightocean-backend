package br.com.fiap.BrightOcean.model;

import br.com.fiap.BrightOcean.dto.diagnostico.AlterarDiagnosticoDTO;
import br.com.fiap.BrightOcean.model.enums.Saude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDiagnostico;
    private Saude saude;
    private LocalDate dataDiagnostico;

    @JsonIgnore
    @OneToOne
    private Fotografia fotografia;

    @JsonIgnore
    @ManyToOne
    private Recife recife;

    public Diagnostico(Saude saude, Fotografia fotografia, Recife recife) {
        this.saude = saude;
        this.dataDiagnostico = LocalDate.now();
        this.fotografia = fotografia;
        this.recife = recife;
    }

    public void atualizarDiagnostico(AlterarDiagnosticoDTO diagnosticoDTO) {
        if(diagnosticoDTO.saude() != null) this.saude = diagnosticoDTO.saude();
    }
}
