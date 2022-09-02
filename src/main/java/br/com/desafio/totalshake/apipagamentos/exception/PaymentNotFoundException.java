package br.com.desafio.totalshake.apipagamentos.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends RuntimeException{

    public PaymentNotFoundException() {
        super("Pagamento nao encontrado");
    }
}
