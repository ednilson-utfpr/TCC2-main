package br.edu.utfpr.pb.tcc.repository;

import br.edu.utfpr.pb.tcc.model.Cobertura;
import br.edu.utfpr.pb.tcc.model.Formato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormatoRepository extends JpaRepository<Formato, Long> {
}
