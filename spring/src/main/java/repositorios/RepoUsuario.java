package repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import modelos.Usuario;

public interface RepoUsuario extends JpaRepository<Usuario, Long>  {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
}
