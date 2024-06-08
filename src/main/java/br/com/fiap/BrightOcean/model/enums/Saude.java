package br.com.fiap.BrightOcean.model.enums;

public enum Saude {
    SAUDAVEL("Saudável"),
    DOENTE("Doente");

    private String status;

    Saude(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Saude fromString(String status) {
        for (Saude s : Saude.values()) {
            if (s.getStatus().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status de saúde desconhecido: " + status);
    }
}
