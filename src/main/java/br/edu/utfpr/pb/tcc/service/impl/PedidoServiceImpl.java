package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.Pedido;
import br.edu.utfpr.pb.tcc.repository.PedidoRepository;
import br.edu.utfpr.pb.tcc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl extends CrudServiceImpl<Pedido, Long>  implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    protected JpaRepository<Pedido, Long> getRepository() {
        return pedidoRepository;
    }

    @Override
    public List<Pedido> findAllByClienteId(Long clienteId) {
        return pedidoRepository.findAllByClienteId(clienteId);
    }
}
