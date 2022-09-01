package br.com.desafio.totalshake.apipagamentos.model;

import br.com.desafio.totalshake.apipagamentos.dto.PagamentoDTO;
import br.com.desafio.totalshake.apipagamentos.enums.FormaDePagamento;
import br.com.desafio.totalshake.apipagamentos.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Getter @Setter
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    private String nome;

    private String numero;

    private String expiracao;

    private String codigo;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "forma_de_pagamento")
    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    // todo MAPPER
    public static Pagamento of(PagamentoDTO dto) {
        var pagamento = new Pagamento();

        pagamento.setValor(dto.getValor());
        pagamento.setNome(dto.getNome());
        pagamento.setNumero(dto.getNumero());
        pagamento.setCodigo(dto.getCodigo());
        pagamento.setStatus(dto.getStatus());
        pagamento.setExpiracao(dto.getExpiracao());
        pagamento.setPedidoId(dto.getPedidoId());

        return pagamento;
    }
}
