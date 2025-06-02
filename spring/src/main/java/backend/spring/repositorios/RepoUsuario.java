package backend.spring.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.Usuario;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Long> {

    List<Usuario> findByUsername(String username);
}