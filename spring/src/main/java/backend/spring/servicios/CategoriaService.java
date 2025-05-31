package backend.spring.servicios;

import java.util.List;
import java.util.Optional;

import backend.spring.modelos.Categoria;

public interface CategoriaService {
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    Categoria save(Categoria categoria);
    void deleteById(Long id);
}
