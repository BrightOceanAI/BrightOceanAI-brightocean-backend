package br.com.fiap.BrightOcean.integration;

import br.com.fiap.BrightOcean.model.Fotografia;
import br.com.fiap.BrightOcean.model.enums.Saude;
import org.springframework.stereotype.Service;

@Service
public class DatascienceIntegration {

    public Saude realizarDiagnostico(Fotografia fotografia){
        return Saude.SAUDÁVEL;
    }
}
