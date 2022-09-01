package br.com.desafio.totalshake.apipagamentos.service;


import br.com.desafio.totalshake.apipagamentos.dto.PagamentoDTO;
import br.com.desafio.totalshake.apipagamentos.enums.Status;
import br.com.desafio.totalshake.apipagamentos.model.Pagamento;
import br.com.desafio.totalshake.apipagamentos.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;
    @Autowired
    private ModelMapper mapper;

    public PagamentoDTO create(PagamentoDTO dto) {
        var pagamentoSaved = repository.save(mapper.map(dto, Pagamento.class));

        return mapper.map(pagamentoSaved, PagamentoDTO.class);
    }

    public PagamentoDTO findById(Long id) {
        var pagamento = repository.findById(id).orElseThrow(() -> new RuntimeException("!!"));
        return mapper.map(pagamento, PagamentoDTO.class);
    }

    public List<PagamentoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(pagamento -> mapper.map(pagamento, PagamentoDTO.class))
                .toList();
    }

    public PagamentoDTO update(Long id, PagamentoDTO dto) {
        var pagamento = repository.findById(id).orElseThrow(() -> new RuntimeException("!!"));
        mapper.map(dto, pagamento);

        return mapper.map(repository.save(pagamento), PagamentoDTO.class);
    }

    public void delete(Long id) {
        var pagamento = repository.findById(id).orElseThrow(() -> new RuntimeException("!!"));
        repository.delete(pagamento);
    }

    public void updatePaymentStatus(Long idPagamento, String status) {
        var pagamento = repository.findById(idPagamento).orElseThrow(() -> new RuntimeException("!!"));

        //TODO update status

        String statusPedido = "";
        if (pagamento.getStatus().equals(Status.CONFIRMADO))
            statusPedido = "PAGO";
        else if (pagamento.getStatus().equals(Status.CANCELADO))
            statusPedido = "CANCELADO";

        String url = String.format("http://localhost:8080/total-shake/api/%s/%s", pagamento.getPedidoId(), statusPedido);
        RestTemplate restTemplate = new RestTemplate();


    }


    // TODO CLEAN CODE

}
