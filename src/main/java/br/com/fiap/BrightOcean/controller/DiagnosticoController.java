package br.com.fiap.BrightOcean.controller;

import br.com.fiap.BrightOcean.dto.diagnostico.AlterarDiagnosticoDTO;
import br.com.fiap.BrightOcean.dto.diagnostico.EnviarDiagnosticoDTO;
import br.com.fiap.BrightOcean.dto.diagnostico.DetalharDiagnosticoDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Diagnostico;
import br.com.fiap.BrightOcean.service.DiagnosticoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/diagnosticos")
@Tag(name = "Diagnosticos")
public class DiagnosticoController {
    @Autowired
    private DiagnosticoService service;

    @Operation(summary = "Realiza um diagnostico na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Diagnostico.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar diagnosticos")})
    @Transactional
    @PostMapping
    public ResponseEntity realizarDiagnostico(@RequestParam Long idRecife, @RequestParam Long idFotografia) throws IOException {
        try {
            DetalharDiagnosticoDTO createdDiagnostico = service.criarDiagnostico(idRecife, idFotografia);
            return new ResponseEntity<>(createdDiagnostico, HttpStatus.CREATED);
        }catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Lista os diagnosticos da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Diagnostico.class))))})
    @GetMapping
    public ResponseEntity<Page<Diagnostico>> getAllDiagnosticos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Diagnostico> diagnosticos = service.listarDiagnosticos(pageable);
        return new ResponseEntity<>(diagnosticos, HttpStatus.OK);
    }


    @Operation(summary = "Retorna um diagnostico da base de dados com base no id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Diagnostico.class))),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar diagnostico")})
    @GetMapping("/{id}")
    public ResponseEntity getDiagnosticoById(@PathVariable("id") Long id) {
        try{
            Diagnostico diagnostico = service.buscarDiagnosticoPorId(id);
            return new ResponseEntity<>(diagnostico, HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Altera um diagnostico na base de dados com base no id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Diagnostico.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar diagnostico")})
    @PutMapping("/{id}")
    public ResponseEntity updateDiagnostico(@PathVariable("id") Long id, @RequestBody @Valid AlterarDiagnosticoDTO diagnosticoDTO) {
        try{
            Diagnostico updatedDiagnostico = service.alterarDiagnostico(id, diagnosticoDTO);
            return new ResponseEntity<>(updatedDiagnostico, HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @Operation(summary = "Deleta um diagnostico na base de dados com base no id", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Diagnostico não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDiagnostico(@PathVariable("id") Long id) {
        try {
            service.deletarDiagnostico(id);
            return new ResponseEntity<>("Sucesso", HttpStatus.NO_CONTENT);
        }catch (BusinessException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
