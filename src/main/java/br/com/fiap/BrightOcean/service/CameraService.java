package br.com.fiap.BrightOcean.service;

import br.com.fiap.BrightOcean.dto.camera.AlterarCameraDTO;
import br.com.fiap.BrightOcean.dto.camera.CriarCameraDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Camera;
import br.com.fiap.BrightOcean.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraService {
    @Autowired
    private CameraRepository cameraRepository;

    public Camera criarCamera(CriarCameraDTO cameraDto) throws BusinessException {
        try {
            Camera camera = new Camera(cameraDto);
            return cameraRepository.save(camera);
        }catch (Exception e){
            throw new BusinessException("Erro ao cadastrar");
        }
    }

    public List<Camera> listarCameras() {
        return cameraRepository.findAll();
    }

    public Camera buscarCameraPorId(Long id) throws BusinessException {
        Optional<Camera> camera = cameraRepository.findById(id);
        if(!camera.isPresent()){
            throw new BusinessException("Erro ao buscar por id");
        }

        return camera.get();
    }

    public Camera alterarCamera(Long id, AlterarCameraDTO cameraDTO) throws BusinessException {
        if (cameraRepository.existsById(id)) {
            Camera camera = new Camera();
            camera.atualizarCamera(cameraDTO);
            return cameraRepository.save(camera);
        } else {
            throw new BusinessException("Erro ao realizar alteração");
        }
    }


    public void deletarCamera(Long id) throws BusinessException {
        if (cameraRepository.existsById(id)) {
            cameraRepository.deleteById(id);
        } else {
            throw new BusinessException("Erro ao realizar alteração");
        }
    }
}