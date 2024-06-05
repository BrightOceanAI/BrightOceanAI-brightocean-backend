package br.com.fiap.BrightOcean.exceptions;

public class BusinessException extends Throwable {
    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
}
