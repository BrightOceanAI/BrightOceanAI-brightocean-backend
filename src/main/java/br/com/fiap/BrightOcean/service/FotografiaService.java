package br.com.fiap.BrightOcean.service;


import br.com.fiap.BrightOcean.config.AzureConfig;
import br.com.fiap.BrightOcean.dto.fotografia.AlterarFotografiaDTO;
import br.com.fiap.BrightOcean.dto.fotografia.DetalharFotografiaDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Camera;
import br.com.fiap.BrightOcean.model.Fotografia;
import br.com.fiap.BrightOcean.repository.CameraRepository;
import br.com.fiap.BrightOcean.repository.FotografiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class FotografiaService {
    @Autowired
    private FotografiaRepository fotografiaRepository;
    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private AzureConfig azureConfig;

    public DetalharFotografiaDTO criarFotografia(MultipartFile image, Long idCamera) throws BusinessException {
        try {
            String linkImagem = azureConfig.uploadImage(image);
            Camera camera = cameraRepository.getReferenceById(idCamera);

            Fotografia fotografiaCadastro = new Fotografia(linkImagem, camera);
            Fotografia fotografia = fotografiaRepository.save(fotografiaCadastro);
            return new DetalharFotografiaDTO(fotografia);
        }catch (Exception e){
            throw new BusinessException("Erro ao cadastrar");
        }
    }

    public List<Fotografia> listarFotografias() {
        return fotografiaRepository.findAll();
    }

    public Fotografia buscarFotografiaPorId(Long id) throws BusinessException {
        Optional<Fotografia> fotografia = fotografiaRepository.findById(id);
        if(!fotografia.isPresent()){
            throw new BusinessException("Erro ao buscar por id");
        }

        return fotografia.get();
    }

    public Fotografia alterarFotografia(Long id, AlterarFotografiaDTO fotografiaDTO) throws BusinessException {
        if (fotografiaRepository.existsById(id)) {
            Fotografia fotografia = fotografiaRepository.getReferenceById(id);
            fotografia.atualizarFotografia(fotografiaDTO);
            return fotografiaRepository.save(fotografia);
        } else {
            throw new BusinessException("Erro ao realizar alteração");
        }
    }


    public void deletarFotografia(Long id) throws BusinessException {
        if (fotografiaRepository.existsById(id)) {
            fotografiaRepository.deleteById(id);
        } else {
            throw new BusinessException("Erro ao realizar alteração");
        }
    }
}
