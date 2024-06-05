package br.com.fiap.BrightOcean.service;

import br.com.fiap.BrightOcean.config.SecurityConfig;
import br.com.fiap.BrightOcean.dto.jwt.JwtTokenDTO;
import br.com.fiap.BrightOcean.dto.login.CadastroLoginDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Usuario;
import br.com.fiap.BrightOcean.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityConfig securityConfig;

    public JwtTokenDTO logar(CadastroLoginDTO cadastroLoginDTO) throws BusinessException {
        try {
            JwtTokenDTO token = this.authenticateUser(cadastroLoginDTO);
            return token;
        }catch (BusinessException e){
            throw new BusinessException("Email ou senha inválidos!");
        }
    }

    public JwtTokenDTO authenticateUser(CadastroLoginDTO loginUserDto) throws BusinessException {
        try {
            Optional<Usuario> usuario = usuarioRepository.findByEmail(loginUserDto.email());

            if(!usuario.isPresent()){
                throw new BusinessException("Email ou senha inválidos!");
            }

            boolean verificaSenha = securityConfig.passwordEncoder().matches(usuario.get().getSenha(), loginUserDto.senha());

            if(verificaSenha){
                throw new BusinessException("Email ou senha inválidos!");
            }

            return new JwtTokenDTO(jwtTokenService.generateToken(usuario.get()));
        } catch (BusinessException e) {
            throw new BusinessException("Email ou senha inválidos!");
        }
    }
}