package br.com.fiap.BrightOcean.service;

import br.com.fiap.BrightOcean.dto.recife.AlterarRecifeDTO;
import br.com.fiap.BrightOcean.dto.recife.CriarRecifeDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Recife;
import br.com.fiap.BrightOcean.repository.DiagnosticoRepository;
import br.com.fiap.BrightOcean.repository.RecifeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecifeService {
    @Autowired
    private RecifeRepository fecifeRepository;
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public Recife criarRecife(CriarRecifeDTO recifeDto) throws BusinessException {
        try {
            Recife recifeCadastro = new Recife(recifeDto);
            return fecifeRepository.save(recifeCadastro);
        }catch (Exception e){
            throw new BusinessException("Erro ao cadastrar");
        }
    }

    public Page<Recife> listarRecifes(Pageable pageable) {
        return fecifeRepository.findAll(pageable);
    }

    public Recife buscarRecifePorId(Long id) throws BusinessException {
        Optional<Recife> fecife = fecifeRepository.findById(id);
        if(!fecife.isPresent()){
            throw new BusinessException("Erro ao buscar por id");
        }

        return fecife.get();
    }

    public Recife alterarRecife(Long id, AlterarRecifeDTO recifeDTO) throws BusinessException {
        if (fecifeRepository.existsById(id)) {
            Recife fecife = fecifeRepository.getReferenceById(id);
            fecife.atualizarRecife(recifeDTO);
            return fecifeRepository.save(fecife);
        } else {
            throw new BusinessException("Erro ao realizar alteração");
        }
    }


    public void deletarRecife(Long id) throws BusinessException {
        if (fecifeRepository.existsById(id)) {
            fecifeRepository.deleteById(id);
        } else {
            throw new BusinessException("Erro ao realizar alteração");
        }
    }
}
