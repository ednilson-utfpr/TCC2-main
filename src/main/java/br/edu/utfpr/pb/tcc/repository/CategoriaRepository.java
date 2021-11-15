package br.edu.utfpr.pb.tcc.repository;

import br.edu.utfpr.pb.tcc.model.Categoria;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

      //public Categoria findCategoriaByCategoriaId(Long categoriaId);
}


