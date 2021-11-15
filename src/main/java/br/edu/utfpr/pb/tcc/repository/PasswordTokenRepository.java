package br.edu.utfpr.pb.tcc.repository;

import br.edu.utfpr.pb.tcc.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
