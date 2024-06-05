package br.com.fiap.BrightOcean.model;

import br.com.fiap.BrightOcean.dto.recife.AlterarRecifeDTO;
import br.com.fiap.BrightOcean.dto.recife.CriarRecifeDTO;
import br.com.fiap.BrightOcean.model.enums.Classificacao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recife", orphanRemoval = true)
    private List<Diagnostico> diagnosticos;

    public Recife(CriarRecifeDTO recifeDto) {
        this.nome = recifeDto.nome();
        this.classificacao = recifeDto.classificacao();
        this.latitude = recifeDto.latitude();
        this.longitude = recifeDto.longitude();
        this.emRestauracao = recifeDto.emRestauracao();
        this.estratgiaRestauracao = recifeDto.estratgiaRestauracao();
    }

    public void atualizarRecife(AlterarRecifeDTO recifeDTO) {
        if(recifeDTO.nome() != null) this.nome = recifeDTO.nome();
        if(recifeDTO.classificacao() != null) this.classificacao = recifeDTO.classificacao();
        if(recifeDTO.latitude() != null) this.latitude = recifeDTO.latitude();
        if(recifeDTO.longitude() != null) this.longitude = recifeDTO.longitude();
        if(recifeDTO.emRestauracao() != null)  this.emRestauracao = recifeDTO.emRestauracao();
        if(recifeDTO.estratgiaRestauracao() != null) this.estratgiaRestauracao = recifeDTO.estratgiaRestauracao();
    }
}
