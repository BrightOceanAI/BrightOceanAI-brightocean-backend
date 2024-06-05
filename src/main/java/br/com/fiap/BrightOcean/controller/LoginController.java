package br.com.fiap.BrightOcean.controller;

import br.com.fiap.BrightOcean.dto.jwt.JwtTokenDTO;
import br.com.fiap.BrightOcean.dto.login.CadastroLoginDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.service.LoginService;
import br.com.fiap.BrightOcean.util.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ErrorMessage error;

    @Operation(summary = "Realiza o login de um usuário no sistema", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = CadastroLoginDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao efetuar login do usuário")})
    @PostMapping("/token")
    public ResponseEntity logar(@RequestBody @Valid CadastroLoginDTO cadastroLoginDTO) {
        try {
            JwtTokenDTO login = loginService.logar(cadastroLoginDTO);
            return ResponseEntity.status(200).body(login);
        } catch (BusinessException | Exception e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }
}