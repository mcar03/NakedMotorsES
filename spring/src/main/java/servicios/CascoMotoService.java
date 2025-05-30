package servicios;

import java.util.List;
import java.util.Optional;

import modelos.CascoMoto;

public interface CascoMotoService {
    List<CascoMoto> findAll();
    Optional<CascoMoto> findById(Long id);
    CascoMoto save(CascoMoto cascoMoto);
    void deleteById(Long id);
}
