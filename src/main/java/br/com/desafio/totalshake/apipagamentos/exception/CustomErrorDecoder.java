package br.com.desafio.totalshake.apipagamentos.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 400 -> new RuntimeException();
            case 404 -> new RuntimeException("client not found");
            case 500 -> new RuntimeException("");
            default -> new RuntimeException("123");
        };
    }
}
