package br.edu.utfpr.pb.tcc.service;

import br.edu.utfpr.pb.tcc.model.Pedido;

import java.util.List;

public interface PedidoService extends CrudService<Pedido, Long> {
    List<Pedido> findAllByClienteId(Long clienteId);
}
