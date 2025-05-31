package backend.spring.servicios;

import java.util.List;
import java.util.Optional;

import backend.spring.modelos.GuantesMoto;

public interface GuantesMotoService {

    List<GuantesMoto> findAll();
    Optional<GuantesMoto> findById(Long id);
    GuantesMoto save(GuantesMoto guantesMoto);
    void deleteById(Long id);
}