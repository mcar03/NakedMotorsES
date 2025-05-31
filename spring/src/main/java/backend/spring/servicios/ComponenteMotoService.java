package backend.spring.servicios;

import java.util.List;
import java.util.Optional;

import backend.spring.modelos.ComponenteMoto;

public interface ComponenteMotoService {
    List<ComponenteMoto> findAll();
    Optional<ComponenteMoto> findById(Long id);
    ComponenteMoto save(ComponenteMoto componenteMoto);
    void deleteById(Long id);
}
