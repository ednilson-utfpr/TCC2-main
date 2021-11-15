package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.Cobertura;
import br.edu.utfpr.pb.tcc.model.Recheio;
import br.edu.utfpr.pb.tcc.repository.CoberturaRepository;
import br.edu.utfpr.pb.tcc.repository.RecheioRepository;
import br.edu.utfpr.pb.tcc.service.CoberturaService;
import br.edu.utfpr.pb.tcc.service.RecheioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RecheioServiceImpl extends CrudServiceImpl<Recheio, Long>  implements RecheioService {

    @Autowired
    private RecheioRepository recheioRepository;

    @Override
    protected JpaRepository<Recheio, Long> getRepository() {
        return recheioRepository;
    }
}
