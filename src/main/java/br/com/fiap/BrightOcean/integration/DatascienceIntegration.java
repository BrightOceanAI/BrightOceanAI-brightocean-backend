package br.com.fiap.BrightOcean.integration;

import br.com.fiap.BrightOcean.model.Fotografia;
import br.com.fiap.BrightOcean.model.enums.Saude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class DatascienceIntegration {

    private static String datascienceUrlBase = "https://bto-coral-ai.azurewebsites.net";

    public Saude realizarDiagnostico(Fotografia fotografia) throws IOException {
        URL url = new URL(datascienceUrlBase + "/prediction");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = "{\"idFotografia\": " + fotografia.getIdFotografia() +
                ", \"link\":\"" + fotografia.getLink() + "\"}";

        try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
            os.writeBytes(jsonInputString);
            os.flush();
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // Sucesso
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper objectMapper = new ObjectMapper();
            DiagnosticoResponse diagnosticoResponse = objectMapper.readValue(response.toString(), DiagnosticoResponse.class);

            Saude saude = Saude.fromString(diagnosticoResponse.getSaúde());
            return saude;
        } else {
            throw new IOException("Erro na requisição: Código de resposta " + responseCode);
        }
    }

}
