package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.Situacao;
import br.edu.utfpr.pb.tcc.repository.SituacaoRepository;
import br.edu.utfpr.pb.tcc.service.SituacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SituacaoServiceImpl extends CrudServiceImpl<Situacao, Long>  implements SituacaoService {

    @Autowired
    private SituacaoRepository situacaoRepository;

    @Override
    protected JpaRepository<Situacao, Long> getRepository() {
        return situacaoRepository;
    }


}
