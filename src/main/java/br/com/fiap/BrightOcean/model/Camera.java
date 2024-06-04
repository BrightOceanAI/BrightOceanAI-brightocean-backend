package br.com.fiap.BrightOcean.model;

import br.com.fiap.BrightOcean.dto.camera.AlterarCameraDTO;
import br.com.fiap.BrightOcean.dto.camera.CriarCameraDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCamera;
    private String modeloCamera;
    private String localizacao;
    private float latitude;
    private float longitude;    

    public Camera(CriarCameraDTO cameraDto) {
        this.modeloCamera = cameraDto.modeloCamera();
        this.localizacao = cameraDto.localizacao();
        this.latitude = cameraDto.latitude();
        this.longitude = cameraDto.longitude();
    }

    public void atualizarCamera(AlterarCameraDTO cameraDTO) {
        if(cameraDTO.modeloCamera() != null){ this.modeloCamera = cameraDTO.modeloCamera(); }
        if(cameraDTO.localizacao() != null){ this.localizacao = cameraDTO.localizacao(); }
        if(cameraDTO.latitude() != null){ this.latitude = cameraDTO.latitude(); }
        if(cameraDTO.longitude() != null){ this.longitude = cameraDTO.longitude(); }
    }
}
