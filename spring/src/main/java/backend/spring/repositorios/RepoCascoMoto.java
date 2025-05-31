package backend.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.CascoMoto;


public interface RepoCascoMoto extends JpaRepository<CascoMoto, Long> {
    
}
