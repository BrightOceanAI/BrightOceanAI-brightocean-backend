package br.com.fiap.BrightOcean.config;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Configuration
public class AzureConfig {

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${azure.blob-storage.connection-string}")
    private String connectionString;

    private BlobServiceClient blobServiceClient;

    @PostConstruct
    public void init(){
        blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    public String uploadImage(MultipartFile image) throws IOException {
        String blobFileName = image.getOriginalFilename();
        BlobClient blobClient =  blobServiceClient
                .getBlobContainerClient(containerName)
                .getBlobClient(blobFileName);

        blobClient.upload(image.getInputStream(), image.getSize(), true);

        return blobClient.getBlobUrl();
    }

}
