package br.com.fiap.BrightOcean.controller;

import br.com.fiap.BrightOcean.dto.recife.AlterarRecifeDTO;
import br.com.fiap.BrightOcean.dto.recife.CriarRecifeDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Recife;
import br.com.fiap.BrightOcean.service.RecifeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recifes")
@Tag(name = "Recifes")
public class RecifeController {
    @Autowired
    private RecifeService service;

    @Operation(summary = "Cadastra um recife na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Recife.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar recife")})
    @PostMapping
    public ResponseEntity createRecife(@RequestBody @Valid CriarRecifeDTO recifedto) {
        try {
            Recife createdRecife = service.criarRecife(recifedto);
            return new ResponseEntity<>(createdRecife, HttpStatus.CREATED);
        }catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Lista os recifes da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Recife.class))))})
    @GetMapping
    public ResponseEntity<List<Recife>> getAllRecifes() {
        List<Recife> recifes = service.listarRecifes();
        return new ResponseEntity<>(recifes, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um recife da base de dados com base no id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Recife.class))),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar recife")})
    @GetMapping("/{id}")
    public ResponseEntity getRecifeById(@PathVariable("id") Long id) {
        try{
            Recife recife = service.buscarRecifePorId(id);
            return new ResponseEntity<>(recife, HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Altera um recife na base de dados com base no id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Recife.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar recife")})
    @PutMapping("/{id}")
    public ResponseEntity updateRecife(@PathVariable("id") Long id, @RequestBody @Valid AlterarRecifeDTO recifeDTO) {
        try{
            Recife updatedRecife = service.alterarRecife(id, recifeDTO);
            return new ResponseEntity<>(updatedRecife, HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @Operation(summary = "Deleta um recife na base de dados com base no id", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Recife não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecife(@PathVariable("id") Long id) {
        try {
            service.deletarRecife(id);
            return new ResponseEntity<>("Sucesso", HttpStatus.NO_CONTENT);
        }catch (BusinessException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
