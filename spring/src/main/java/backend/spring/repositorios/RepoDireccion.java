package backend.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.Direccion;
import backend.spring.modelos.Usuario;


@Repository
public interface RepoDireccion extends JpaRepository<Direccion, Long> {

    List<Direccion> findByUsuario(Usuario usuario);
}
