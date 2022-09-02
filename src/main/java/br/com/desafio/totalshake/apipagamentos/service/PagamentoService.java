package br.com.desafio.totalshake.apipagamentos.service;


import br.com.desafio.totalshake.apipagamentos.dto.PagamentoDTO;
import br.com.desafio.totalshake.apipagamentos.enums.Status;
import br.com.desafio.totalshake.apipagamentos.exception.PaymentNotFoundException;
import br.com.desafio.totalshake.apipagamentos.model.Pagamento;
import br.com.desafio.totalshake.apipagamentos.client.TotalShakeClient;
import br.com.desafio.totalshake.apipagamentos.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Pagamento pagamento = findPayment(id);
        return mapper.map(pagamento, PagamentoDTO.class);
    }

    public List<PagamentoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(pagamento -> mapper.map(pagamento, PagamentoDTO.class))
                .toList();
    }

    public PagamentoDTO update(Long id, PagamentoDTO dto) {
        Pagamento pagamento = findPayment(id);
        dto.setId(pagamento.getId());
        mapper.map(dto, pagamento);



        return mapper.map(repository.save(pagamento), PagamentoDTO.class);
    }

    public void delete(Long id) {
        Pagamento pagamento = findPayment(id);
        repository.delete(pagamento);
    }


    @Autowired
    private TotalShakeClient client;

    public PagamentoDTO updatePaymentStatus(Long idPagamento, Status status) {
        Pagamento pagamento = findPayment(idPagamento);
        pagamento.setStatus(status);

        var updatedPagamento = repository.save(pagamento);
        syncWithTotalShake(updatedPagamento);

        return mapper.map(updatedPagamento, PagamentoDTO.class);

    }

    private Pagamento findPayment(Long idPagamento) {
        return repository.findById(idPagamento).orElseThrow(PaymentNotFoundException::new);
    }

    private void syncWithTotalShake(Pagamento updatedPagamento) {
        String statusPedido = "";
        if (updatedPagamento.getStatus().equals(Status.CONFIRMADO))
            statusPedido = "PAGO";
        else if (updatedPagamento.getStatus().equals(Status.CANCELADO))
            statusPedido = "CANCELADO";

        try {
            client.atualizaStatusPedido(updatedPagamento.getPedidoId(), statusPedido);
        } catch (Exception ex) {
            System.out.println("Err - total shake api: " + ex.getMessage());
        }
    }



}
