package br.com.desafio.totalshake.apipagamentos.controller;


import br.com.desafio.totalshake.apipagamentos.dto.PagamentoDTO;
import br.com.desafio.totalshake.apipagamentos.enums.Status;
import br.com.desafio.totalshake.apipagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PagamentoDTO dto) {
        var pagamentoSaved = service.create(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idPagamento}")
                .buildAndExpand(pagamentoSaved.getId())
                .toUri();

        return  ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok( service.findAll());
    }

    @GetMapping("{idPagamento}")
    public ResponseEntity<?> findById(@RequestParam Long idPagamento) {
        return ResponseEntity.ok( service.findById(idPagamento));
    }

    @PutMapping("/{idPagamento}")
    public ResponseEntity<?> update(@RequestParam Long idPagamento, @RequestBody PagamentoDTO dto) {
        return ResponseEntity.ok( service.update(idPagamento, dto) );
    }

    @DeleteMapping("/{idPagamento}")
    public ResponseEntity<?> delete(@RequestParam Long idPagamento) {
        service.delete(idPagamento);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idPagamento}/{status}")
    public ResponseEntity<?> updateStatusPagamento(@PathVariable Long idPagamento, @PathVariable Status status) {
        return ResponseEntity.ok(service.updatePaymentStatus(idPagamento, status));
    }

}
