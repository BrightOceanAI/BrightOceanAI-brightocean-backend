package br.com.fiap.BrightOcean.model;

import br.com.fiap.BrightOcean.dto.usuario.AtualizacaoUsuarioDTO;
import br.com.fiap.BrightOcean.dto.usuario.CadastroUsuarioDTO;
import br.com.fiap.BrightOcean.model.enums.Perfil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Perfil perfil;

    public Usuario(CadastroUsuarioDTO cadastroUsuarioDTO, String senhaCriptograda) {
        this.nome = cadastroUsuarioDTO.nome();
        this.cpf = cadastroUsuarioDTO.cpf();
        this.email = cadastroUsuarioDTO.email();
        this.senha = senhaCriptograda;
        this.perfil = cadastroUsuarioDTO.perfil();
    }

    public void atualizarUsuario(AtualizacaoUsuarioDTO atualizacaoUsuarioDTO, String senhaCriptograda) {
        if(atualizacaoUsuarioDTO.nome() != null) this.nome = atualizacaoUsuarioDTO.nome();
        if(atualizacaoUsuarioDTO.email() != null) this.email = atualizacaoUsuarioDTO.email();
        if(atualizacaoUsuarioDTO.perfil() != null) this.perfil = atualizacaoUsuarioDTO.perfil();
        if(atualizacaoUsuarioDTO.senha() != null) this.senha = senhaCriptograda;
    }
}
