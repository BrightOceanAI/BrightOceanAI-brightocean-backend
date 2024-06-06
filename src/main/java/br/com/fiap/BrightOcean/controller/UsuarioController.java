package br.com.fiap.BrightOcean.controller;

import br.com.fiap.BrightOcean.dto.usuario.AtualizacaoUsuarioDTO;
import br.com.fiap.BrightOcean.dto.usuario.CadastroUsuarioDTO;
import br.com.fiap.BrightOcean.dto.usuario.DetalhamentoUsuarioDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Usuario;
import br.com.fiap.BrightOcean.model.UsuarioModelAssembler;
import br.com.fiap.BrightOcean.service.UsuarioService;
import br.com.fiap.BrightOcean.util.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;

@Tag(name = "Usuário")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ErrorMessage error;

    private final UsuarioModelAssembler assembler;

    public UsuarioController(UsuarioService usuarioService, UsuarioModelAssembler assembler) {
        this.usuarioService = usuarioService;
        this.assembler = assembler;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity buscarPorEmail(@PathVariable @Email String email) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
            EntityModel<Usuario> usuarioModel = assembler.toModel(usuario);
            return ResponseEntity.ok(usuarioModel);
        } catch (BusinessException e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }


    @Operation(summary = "Cadastra um usuário na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar usuário")})
    @PostMapping("/cadastro")
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroUsuarioDTO cadastroUsuarioDTO){
        try {
            DetalhamentoUsuarioDTO usuario = usuarioService.cadastrarUsuario(cadastroUsuarioDTO);
            return ResponseEntity.status(201).body(usuario);
        }catch (Exception | BusinessException e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Lista os usuários da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class))))})
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.status(200).body(usuarios);
    }

    @Operation(summary = "Retorna um usuário na base de dados com base no id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar usuário")})
    @GetMapping("/id/   {id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.status(200).body(usuario);
        }catch (BusinessException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Altera um usuário na base de dados com base no id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar usuário")})
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioDTO atualizacaoUsuarioDTO){
        try {
            DetalhamentoUsuarioDTO usuario = usuarioService.atualizarUsuario(id, atualizacaoUsuarioDTO);
            return ResponseEntity.status(200).body(usuario);
        }catch (BusinessException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Deleta um usuário na base de dados com base no id", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar usuário")})
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.status(204).build();
        }catch (BusinessException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }
}