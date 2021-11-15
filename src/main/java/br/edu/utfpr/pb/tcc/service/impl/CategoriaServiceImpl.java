package br.edu.utfpr.pb.tcc.service.impl;

import br.edu.utfpr.pb.tcc.model.Categoria;
import br.edu.utfpr.pb.tcc.repository.CategoriaRepository;
import br.edu.utfpr.pb.tcc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl extends CrudServiceImpl<Categoria, Long>  implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	protected JpaRepository<Categoria, Long> getRepository() {
		return categoriaRepository;
	}


	//@Override
	public Categoria findCategoriaByCategoriaId(Long categoriaId) {
//		return categoriaRepository.findCategoriaByCategoriaId(categoriaId);
	return null;
	}

}
