package backend.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.Telefono;
import backend.spring.modelos.Usuario;


@Repository
public interface RepoTelefono extends JpaRepository<Telefono, Long> {

    List<Telefono> findByUsuario(Usuario usuario);
}
