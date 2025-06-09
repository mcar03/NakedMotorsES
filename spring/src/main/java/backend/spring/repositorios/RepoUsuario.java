package backend.spring.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.Usuario;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Long> {

     Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
}