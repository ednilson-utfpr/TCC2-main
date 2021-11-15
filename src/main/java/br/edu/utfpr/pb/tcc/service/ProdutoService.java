package br.edu.utfpr.pb.tcc.service;

import br.edu.utfpr.pb.tcc.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoService extends CrudService<Produto, Long>{
    List<Produto> findByNomeContaining(String nome);
    List<Produto> findAllByCategoria_Id(Long id);
    Page<Produto> findAllByCategoria_Id(Long id, Pageable pageable);
}
