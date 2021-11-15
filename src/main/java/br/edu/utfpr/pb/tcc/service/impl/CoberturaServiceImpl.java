package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.Cobertura;
import br.edu.utfpr.pb.tcc.repository.CoberturaRepository;
import br.edu.utfpr.pb.tcc.service.CoberturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CoberturaServiceImpl extends CrudServiceImpl<Cobertura, Long>  implements CoberturaService {

    @Autowired
    private CoberturaRepository coberturaRepository;

    @Override
    protected JpaRepository<Cobertura, Long> getRepository() {
        return coberturaRepository;
}
}
