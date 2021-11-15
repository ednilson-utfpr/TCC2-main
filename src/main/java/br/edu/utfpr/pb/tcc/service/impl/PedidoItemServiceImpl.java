package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.PedidoItem;
import br.edu.utfpr.pb.tcc.repository.PedidoItemRepository;
import br.edu.utfpr.pb.tcc.service.PedidoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoItemServiceImpl extends CrudServiceImpl<PedidoItem, Long>  implements PedidoItemService {

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Override
    protected JpaRepository<PedidoItem, Long> getRepository() {
        return pedidoItemRepository;
    }

    @Override
    public List<PedidoItem> findAllByPedido_Cliente_Id(Long clienteId) {
        return pedidoItemRepository.findAllByPedido_Cliente_Id(clienteId);
    }

    @Override
    public List<PedidoItem> findAllByPedidoId(Long pedidoId) {
        return pedidoItemRepository.findAllByPedidoId(pedidoId);
    }
}