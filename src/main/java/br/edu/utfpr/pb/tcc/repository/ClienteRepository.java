package br.edu.utfpr.pb.tcc.repository;

import br.edu.utfpr.pb.tcc.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findClienteByUsuarioId(Long usuarioId);
}
