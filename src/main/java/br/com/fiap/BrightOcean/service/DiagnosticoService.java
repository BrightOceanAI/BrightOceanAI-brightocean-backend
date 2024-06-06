package br.com.fiap.BrightOcean.service;

import br.com.fiap.BrightOcean.dto.diagnostico.AlterarDiagnosticoDTO;
import br.com.fiap.BrightOcean.dto.diagnostico.CriarDiagnosticoDTO;
import br.com.fiap.BrightOcean.dto.diagnostico.DetalharDiagnosticoDTO;
import br.com.fiap.BrightOcean.exceptions.BusinessException;
import br.com.fiap.BrightOcean.model.Diagnostico;
import br.com.fiap.BrightOcean.model.Fotografia;
import br.com.fiap.BrightOcean.model.Recife;
import br.com.fiap.BrightOcean.repository.DiagnosticoRepository;
import br.com.fiap.BrightOcean.repository.FotografiaRepository;
import br.com.fiap.BrightOcean.repository.RecifeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

    import java.util.Optional;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private FotografiaRepository fotografiaRepository;

    @Autowired
    private RecifeRepository recifeRepository;


    public DetalharDiagnosticoDTO criarDiagnostico(CriarDiagnosticoDTO diagnosticoDto) throws BusinessException {
        try {
            Fotografia fotografia = fotografiaRepository.getReferenceById(diagnosticoDto.idFotografia());
            Recife recife = recifeRepository.getReferenceById(diagnosticoDto.idRecife());

            //Saude saude = iaIntegration.realizarDiagnostico(fotografia);

            Diagnostico diagnosticoCadastro = new Diagnostico(saude, fotografia, recife);
            Diagnostico diagnostico = diagnosticoRepository.save(diagnosticoCadastro);
            return new DetalharDiagnosticoDTO(diagnostico);
        }catch (Exception e){
            throw new BusinessException("Erro ao cadastrar");
        }
    }

    public Page<Diagnostico> listarDiagnosticos(Pageable pageable) {
        return diagnosticoRepository.findAll(pageable);
    }

    public Diagnostico buscarDiagnosticoPorId(Long id) throws BusinessException {
        Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(id);
        if(!diagnostico.isPresent()){
            throw new BusinessException("Erro ao buscar por id");
        }

        return diagnostico.get();
    }

    public Diagnostico alterarDiagnostico(Long id, AlterarDiagnosticoDTO diagnosticoDTO) throws BusinessException {
        if (diagnosticoRepository.existsById(id)) {
            Diagnostico diagnostico = diagnosticoRepository.getReferenceById(id);
            diagnostico.atualizarDiagnostico(diagnosticoDTO);
            return diagnosticoRepository.save(diagnostico);
        } else {
            throw new BusinessException("Erro ao realizar alteração");
        }
    }


    public void deletarDiagnostico(Long id) throws BusinessException {
        if (diagnosticoRepository.existsById(id)) {
            diagnosticoRepository.deleteById(id);
        } else {
            throw new BusinessException("Erro ao realizar alteração");
        }
    }
}
