package br.com.fiap.BrightOcean.controller;

import br.com.fiap.BrightOcean.dto.fotografia.AlterarFotografiaDTO;
import br.com.fiap.BrightOcean.dto.fotografia.CriarFotografiaDTO;
import br.com.fiap.BrightOcean.dto.fotografia.DetalharFotografiaDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Fotografia;
import br.com.fiap.BrightOcean.service.FotografiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/fotografias")
@Tag(name = "Fotografias")
public class FotografiaController {
    @Autowired
    private FotografiaService service;

    @Operation(summary = "Cadastra uma fotografia na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Fotografia.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar fotografia")})
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity registrarFoto(
            @RequestParam(value = "image", required = true) MultipartFile image,
            @RequestParam(value = "idCamera", required = true) Long idCamera
    ) {
        try {
            DetalharFotografiaDTO createdFotografia = service.criarFotografia(image, idCamera);
            return new ResponseEntity<>(createdFotografia, HttpStatus.CREATED);
        }catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Lista as fotografias da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Fotografia.class))))})
    @GetMapping
    public ResponseEntity<List<Fotografia>> getAllFotografias() {
        List<Fotografia> fotografias = service.listarFotografias();
        return new ResponseEntity<>(fotografias, HttpStatus.OK);
    }

    @Operation(summary = "Retorna uma fotografia da base de dados com base no id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Fotografia.class))),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar fotografia")})
    @GetMapping("/{id}")
    public ResponseEntity getFotografiaById(@PathVariable("id") Long id) {
        try{
            Fotografia fotografia = service.buscarFotografiaPorId(id);
            return new ResponseEntity<>(fotografia, HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
