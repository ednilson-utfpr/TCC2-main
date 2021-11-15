package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.Cliente;
import br.edu.utfpr.pb.tcc.repository.ClienteRepository;
import br.edu.utfpr.pb.tcc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends CrudServiceImpl<Cliente, Long>  implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected JpaRepository<Cliente, Long> getRepository() {
        return clienteRepository;
    }

    @Override
    public Cliente findClienteByUsuarioId(Long usuarioId) {
        return clienteRepository.findClienteByUsuarioId(usuarioId);
    }
}
