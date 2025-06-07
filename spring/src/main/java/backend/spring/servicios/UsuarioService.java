package backend.spring.servicios;

import java.util.List;
import java.util.Optional;

import backend.spring.modelos.Usuario;

public interface UsuarioService {
    

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
    Optional<Usuario> findByUsername(String username);

}

