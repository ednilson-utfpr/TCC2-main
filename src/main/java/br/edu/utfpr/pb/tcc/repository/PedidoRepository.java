package br.edu.utfpr.pb.tcc.repository;

import br.edu.utfpr.pb.tcc.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByClienteId(Long clienteId);
}
