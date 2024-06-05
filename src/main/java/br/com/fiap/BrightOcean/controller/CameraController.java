package br.com.fiap.BrightOcean.controller;

import br.com.fiap.BrightOcean.util.ErrorMessage;
import br.com.fiap.BrightOcean.dto.camera.CriarCameraDTO;
import br.com.fiap.BrightOcean.dto.camera.AlterarCameraDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Camera;
import br.com.fiap.BrightOcean.service.CameraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cameras")
@Tag(name = "Câmeras")
public class CameraController {

    @Autowired
    private CameraService service;
    @Autowired
    private ErrorMessage error;

    @Operation(summary = "Cadastra uma câmera na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Camera.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar câmera")})
    @PostMapping
    public ResponseEntity createCamera(@RequestBody CriarCameraDTO cameradto) {
        try {
            Camera createdCamera = service.criarCamera(cameradto);
            return new ResponseEntity<>(createdCamera, HttpStatus.CREATED);
        }catch (BusinessException e) {
            error.setError(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Lista as câmeras da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Camera.class))))})
    @GetMapping
    public ResponseEntity<List<Camera>> getAllCameras() {
        List<Camera> cameras = service.listarCameras();
        return new ResponseEntity<>(cameras, HttpStatus.OK);
    }

    @Operation(summary = "Retorna uma câmera da base de dados com base no id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Camera.class))),
            @ApiResponse(responseCode = "404", description = "Erro ao buscar câmera")})
    @GetMapping("/{id}")
    public ResponseEntity getCameraById(@PathVariable("id") Long id) {
        try{
            Camera camera = service.buscarCameraPorId(id);
            return new ResponseEntity<>(camera, HttpStatus.OK);
        }catch (BusinessException e){
            error.setError(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Altera uma câmera na base de dados com base no id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Camera.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar câmera")})
    @PutMapping("/{id}")
    public ResponseEntity updateCamera(@PathVariable("id") Long id, @RequestBody AlterarCameraDTO cameraDTO) {
        try{
            Camera updatedCamera = service.alterarCamera(id, cameraDTO);
            return new ResponseEntity<>(updatedCamera, HttpStatus.OK);
        }catch (BusinessException e){
            error.setError(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }


    @Operation(summary = "Deleta uma câmera na base de dados com base no id", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso"),
                    @ApiResponse(responseCode = "404", description = "Câmera não encontrada")})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCamera(@PathVariable("id") Long id) {
        try {
            service.deletarCamera(id);
            return new ResponseEntity<>("Sucesso", HttpStatus.NO_CONTENT);
        }catch (BusinessException e){
            error.setError(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }
}
