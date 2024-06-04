package br.com.fiap.BrightOcean.exceptions;

public class BusinessException extends Throwable {
    private String message;

    public BusinessException(String erroAoRealizarAlteração) {
        this.message = erroAoRealizarAlteração;
    }
}
