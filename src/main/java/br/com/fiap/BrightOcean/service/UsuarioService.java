package br.com.fiap.BrightOcean.service;


import br.com.fiap.BrightOcean.config.SecurityConfig;
import br.com.fiap.BrightOcean.dto.usuario.AtualizacaoUsuarioDTO;
import br.com.fiap.BrightOcean.dto.usuario.CadastroUsuarioDTO;
import br.com.fiap.BrightOcean.dto.usuario.DetalhamentoUsuarioDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Usuario;
import br.com.fiap.BrightOcean.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public DetalhamentoUsuarioDTO cadastrarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO) throws BusinessException {
        Optional<Usuario> usuarioByEmail = usuarioRepository.findByEmail(cadastroUsuarioDTO.email());

        if(usuarioByEmail.isPresent()){
            throw new BusinessException("O usuário informado já possui um cadasto.");
        }

        String senhaCriptografada = securityConfig.passwordEncoder().encode(cadastroUsuarioDTO.senha());
        Usuario usuario = new Usuario(cadastroUsuarioDTO, senhaCriptografada);
        usuarioRepository.save(usuario);
        DetalhamentoUsuarioDTO usuarioDTO = new DetalhamentoUsuarioDTO(usuario);
        return usuarioDTO;
    }

    public Page<Usuario> listarUsuarios(Pageable pageable){
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios;
    }

    public Usuario buscarUsuarioPorEmail(String email) throws BusinessException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if(!usuario.isPresent()){
            throw new BusinessException("Não foi possivel encontrar o usuário");
        }

        return usuario.get();
    }

    public Usuario buscarUsuarioPorId(Long id) throws BusinessException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(!usuario.isPresent()){
            throw new BusinessException("Não foi possivel encontrar o usuário");
        }

        return usuario.get();
    }

    public DetalhamentoUsuarioDTO atualizarUsuario(Long id, AtualizacaoUsuarioDTO atualizacaoUsuarioDTO) throws BusinessException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(!usuario.isPresent()){
            throw new BusinessException("Não foi possivel encontrar o usuário");
        }


        String senhaCriptografada = securityConfig.passwordEncoder().encode(atualizacaoUsuarioDTO.senha());
        usuario.get().atualizarUsuario(atualizacaoUsuarioDTO, senhaCriptografada);
        usuarioRepository.save(usuario.get());

        DetalhamentoUsuarioDTO usuarioDTO = new DetalhamentoUsuarioDTO(usuario.get());
        return usuarioDTO;
    }

    public void deletarUsuario(Long id) throws BusinessException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(!usuario.isPresent()){
            throw new BusinessException("Não foi possivel encontrar o usuário");
        }

        usuarioRepository.delete(usuario.get());
    }
}