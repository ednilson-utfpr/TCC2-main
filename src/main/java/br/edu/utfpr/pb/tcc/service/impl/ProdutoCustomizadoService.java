package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.Massa;
import br.edu.utfpr.pb.tcc.model.ProdutoCustomizado;
import br.edu.utfpr.pb.tcc.repository.ProdutoCustomizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoCustomizadoService extends CrudServiceImpl<ProdutoCustomizado, Long>  implements br.edu.utfpr.pb.tcc.service.ProdutoCustomizadoService {

@Autowired
private ProdutoCustomizadoRepository produtoCustomizadoRepository;

@Override
protected JpaRepository<ProdutoCustomizado, Long> getRepository() {
        return produtoCustomizadoRepository;
        }
}
