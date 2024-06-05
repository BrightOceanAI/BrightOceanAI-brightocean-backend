package br.com.fiap.BrightOcean.util;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ErrorMessage {
    private String error;
}
