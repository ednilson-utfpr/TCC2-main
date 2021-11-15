package br.edu.utfpr.pb.tcc.service;

import br.edu.utfpr.pb.tcc.model.Usuario;

public interface UsuarioService extends CrudService<Usuario, Long>{
    Usuario findByEmail(String email);
    Usuario findByUsername(String username);
    void updatePassword(String password, Long userId);
}
