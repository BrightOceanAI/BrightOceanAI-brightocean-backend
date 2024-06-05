package br.com.fiap.BrightOcean.service;

import br.com.fiap.BrightOcean.model.Usuario;
import br.com.fiap.BrightOcean.repository.UsuarioRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioRepository = userRepository.findByEmail(username);

        if(usuarioRepository.isPresent()){
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return User.withUsername(usuarioRepository.get().getEmail())
                .password(usuarioRepository.get().getSenha())
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}