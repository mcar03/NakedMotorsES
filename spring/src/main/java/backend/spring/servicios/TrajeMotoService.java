package backend.spring.servicios;

import java.util.List;
import java.util.Optional;

import backend.spring.modelos.TrajeMoto;

public interface TrajeMotoService {
    List<TrajeMoto> findAll();
    Optional<TrajeMoto> findById(Long id);
    TrajeMoto save(TrajeMoto trajeMoto);
    void deleteById(Long id);
}
