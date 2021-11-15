package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.Massa;
import br.edu.utfpr.pb.tcc.repository.MassaRepository;
import br.edu.utfpr.pb.tcc.service.MassaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MassaServiceImpl extends CrudServiceImpl<Massa, Long>  implements MassaService {

    @Autowired
    private MassaRepository massaRepository;

    @Override
    protected JpaRepository<Massa, Long> getRepository() {
        return massaRepository;
    }


}
