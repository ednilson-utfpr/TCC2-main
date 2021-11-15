package br.edu.utfpr.pb.tcc.service;

import br.edu.utfpr.pb.tcc.model.Cliente;

public interface ClienteService extends CrudService<Cliente, Long> {
    Cliente findClienteByUsuarioId(Long usuarioId);
}
