package br.com.fiap.BrightOcean.integration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiagnosticoResponse {
    @JsonProperty("Saúde")
    private String saúde;

    public String getSaúde() {
        return saúde;
    }

    public void setSaúde(String saúde) {
        this.saúde = saúde;
    }
}
