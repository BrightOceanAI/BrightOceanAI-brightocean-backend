package br.com.fiap.BrightOcean.controller;

import br.com.fiap.BrightOcean.dto.diagnostico.AlterarDiagnosticoDTO;
import br.com.fiap.BrightOcean.dto.diagnostico.CriarDiagnosticoDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnosticos")
@Tag(name = "Diagnosticos")
public class DiagnosticoController {
    @Autowired
    private DiagnosticoService service;

    @Operation(summary = "Cadastra um diagnostico na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Diagnostico.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar fotografia")})
    @PostMapping
    public ResponseEntity createDiagnostico(@RequestBody @Valid CriarDiagnosticoDTO diagnosticodto) {
        try {
            DetalharDiagnosticoDTO createdDiagnostico = service.criarDiagnostico(diagnosticodto);
            return new ResponseEntity<>(createdDiagnostico, HttpStatus.CREATED);
        }catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Lista os diagnosticos da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Diagnostico.class))))})
    @GetMapping
    public ResponseEntity<List<Diagnostico>> getAllDiagnosticos() {
        List<Diagnostico> diagnosticos = service.listarDiagnosticos();
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
