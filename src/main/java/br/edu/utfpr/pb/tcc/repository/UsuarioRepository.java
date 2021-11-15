package br.edu.utfpr.pb.tcc.repository;

import br.edu.utfpr.pb.tcc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
	Usuario findByUsername(String username);

	@Modifying
	@Query("update Usuario u set u.password = :password where u.id = :id")
	void updatePassword(@Param("password") String password, @Param("id") Long id);
}
