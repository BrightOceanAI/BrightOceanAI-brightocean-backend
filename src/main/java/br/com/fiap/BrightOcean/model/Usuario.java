package br.com.fiap.BrightOcean.model;

import br.com.fiap.BrightOcean.model.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Usuario {
    private Long idUsuario;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Perfil perfil;
}
