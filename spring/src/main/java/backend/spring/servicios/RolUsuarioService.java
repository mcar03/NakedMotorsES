package backend.spring.servicios;

import java.util.List;
import java.util.Optional;

import backend.spring.modelos.RolUsuario;

public interface RolUsuarioService {
    List<RolUsuario> findAll();
    Optional<RolUsuario> findById(Long id);
    RolUsuario save(RolUsuario rolUsuario);
    void deleteById(Long id);
}
