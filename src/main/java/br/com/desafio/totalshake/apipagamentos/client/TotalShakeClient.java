package br.com.desafio.totalshake.apipagamentos.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(url = "http://localhost:8080/total-shake/api/", name = "TotalShakeAPI")
public interface TotalShakeClient {


    @PutMapping("pedidos/{idPedido}/{status}")
    void atualizaStatusPedido(@PathVariable Long idPedido, @PathVariable String status);


}
