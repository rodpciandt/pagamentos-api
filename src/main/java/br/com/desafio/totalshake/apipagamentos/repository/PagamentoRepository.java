package br.com.desafio.totalshake.apipagamentos.repository;


import br.com.desafio.totalshake.apipagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
