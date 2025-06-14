package backend.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.RolUsuario;
import backend.spring.modelos.Usuario;

@Repository
public interface RepoRolUsuario extends JpaRepository<RolUsuario, Long> {
    List<RolUsuario> findByUsuario(Usuario usuario);
}
