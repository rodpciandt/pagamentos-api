package br.com.desafio.totalshake.apipagamentos.service;


import br.com.desafio.totalshake.apipagamentos.dto.PagamentoDTO;
import br.com.desafio.totalshake.apipagamentos.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class PagamentoServiceTest {




    @Autowired
    private PagamentoService service;

    @Test
    void test_create() {
        var dto = new PagamentoDTO();

        dto.setNome("Payment 001");
        dto.setCodigo("313");
        dto.setExpiracao("Due today");
        dto.setNumero("number 0321");
        dto.setPedidoId(1L);
        dto.setStatus(Status.CRIADO);

        var pagamentoDTO = service.create(dto);

        System.out.println(pagamentoDTO);
    }


    @Test
    void test_findById() {

    }


    @Test
    @Transactional
    void test_update() {
        var dto = new PagamentoDTO();

        dto.setNome("Payment 001 - updated1234");
        dto.setCodigo("313 - updated ");
        dto.setExpiracao("Due today - updated");
        dto.setNumero("number 0321 - updated");
        dto.setPedidoId(1L);
        dto.setStatus(Status.CRIADO);


        var pagamentoDTO = service.update(1L, dto);

        System.out.println(pagamentoDTO);

    }


    @Test
    void test_integracao() {

    }

}
