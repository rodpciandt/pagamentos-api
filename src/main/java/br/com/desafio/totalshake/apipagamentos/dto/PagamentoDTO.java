package br.com.desafio.totalshake.apipagamentos.dto;

import br.com.desafio.totalshake.apipagamentos.enums.FormaDePagamento;
import br.com.desafio.totalshake.apipagamentos.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class PagamentoDTO {


    @JsonProperty("pagamento_id")
    private Long id;

    @JsonProperty("pedido_id")
    private Long pedidoId;

    private String expiracao;

    @NotNull
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotBlank
    @Size(max = 100, message = "nome deve ter no maximo 100 caracteres")
    private String nome;

    @NotBlank
    @Size(max = 100, message = "numero deve ter no maximo 100 caracteres")
    private String numero;

    @NotBlank
    @Size(min = 3, max = 3, message = "Codigo deve ter 3 caracteres")
    private String codigo;

    @NotNull
    private Status status;

    @NotNull
    private FormaDePagamento formaDePagamento;
}
