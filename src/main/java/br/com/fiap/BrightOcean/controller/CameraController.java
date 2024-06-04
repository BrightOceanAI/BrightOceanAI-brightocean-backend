package br.com.fiap.BrightOcean.controller;

import br.com.fiap.BrightOcean.dto.camera.CriarCameraDTO;
import br.com.fiap.BrightOcean.dto.camera.AlterarCameraDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Camera;
import br.com.fiap.BrightOcean.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cameras")
public class CameraController {

    @Autowired
    private CameraService service;

    @PostMapping
    public ResponseEntity createCamera(@RequestBody CriarCameraDTO cameradto) {
        try {
            Camera createdCamera = service.criarCamera(cameradto);
            return new ResponseEntity<>(createdCamera, HttpStatus.CREATED);
        }catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<List<Camera>> getAllCameras() {
        List<Camera> cameras = service.listarCameras();
        return new ResponseEntity<>(cameras, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity getCameraById(@PathVariable("id") Long id) {
        try{
            Camera camera = service.buscarCameraPorId(id);
            return new ResponseEntity<>(camera, HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity updateCamera(@PathVariable("id") Long id, @RequestBody AlterarCameraDTO cameraDTO) {
        try{
            Camera updatedCamera = service.alterarCamera(id, cameraDTO);
            return new ResponseEntity<>(updatedCamera, HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCamera(@PathVariable("id") Long id) {
        try {
            service.deletarCamera(id);
            return new ResponseEntity<>("Sucesso", HttpStatus.NO_CONTENT);
        }catch (BusinessException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
