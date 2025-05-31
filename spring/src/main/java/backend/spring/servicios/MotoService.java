package backend.spring.servicios;

import java.util.List;
import java.util.Optional;

import backend.spring.modelos.Moto;

public interface MotoService {
    List<Moto> findAll();
    Optional<Moto> findById(Long id);
    Moto save(Moto moto);
    void deleteById(Long id);
}
