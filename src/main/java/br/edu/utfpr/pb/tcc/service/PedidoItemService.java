package br.edu.utfpr.pb.tcc.service;

import br.edu.utfpr.pb.tcc.model.PedidoItem;

import java.util.List;

public interface PedidoItemService extends CrudService<PedidoItem, Long> {
    List<PedidoItem> findAllByPedido_Cliente_Id(Long clienteId);
    List<PedidoItem> findAllByPedidoId(Long pedidoId);
}
