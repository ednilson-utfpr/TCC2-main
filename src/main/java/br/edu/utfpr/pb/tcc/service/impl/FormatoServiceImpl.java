package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.Cobertura;
import br.edu.utfpr.pb.tcc.model.Formato;
import br.edu.utfpr.pb.tcc.repository.CoberturaRepository;
import br.edu.utfpr.pb.tcc.repository.FormatoRepository;
import br.edu.utfpr.pb.tcc.service.CoberturaService;
import br.edu.utfpr.pb.tcc.service.FormatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FormatoServiceImpl extends CrudServiceImpl<Formato, Long>  implements FormatoService {

    @Autowired
    private FormatoRepository formatoRepository;

    @Override
    protected JpaRepository<Formato, Long> getRepository() {
        return formatoRepository;
    }
}
