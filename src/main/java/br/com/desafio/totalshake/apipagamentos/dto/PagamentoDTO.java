package br.com.desafio.totalshake.apipagamentos.dto;

import br.com.desafio.totalshake.apipagamentos.enums.FormaDePagamento;
import br.com.desafio.totalshake.apipagamentos.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @Min(value = 0, message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotBlank
    @Max(value = 10, message = "nome deve ter no maximo 100 caracteres")
    private String nome;

    @NotBlank
    @Max(value = 10, message = "numero deve ter no maximo 100 caracteres")
    private String numero;


    // TODO
    @NotBlank
    @Min(value = 3, message = "codigo deve ter no minimo 3 caracteres")
    @Max(value = 3, message = "codigo deve ter no maximo 100 caracteres")
    private String codigo;

    @NotNull
    private Status status;

    private FormaDePagamento formaDePagamento;
}
