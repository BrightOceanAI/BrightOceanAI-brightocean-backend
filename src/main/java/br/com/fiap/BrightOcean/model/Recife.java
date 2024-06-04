package br.com.fiap.BrightOcean.model;

import br.com.fiap.BrightOcean.model.enums.Classificacao;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Recife {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRecife;
    private String nome;
    private Classificacao classificacao;
    private float latitude;
    private float longitude;
    private boolean emRestauracao;
    private String estratgiaRestauracao;

    @ManyToOne
    private List<Diagnostico> diagnosticos;
}
