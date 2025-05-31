package backend.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.spring.modelos.GuantesMoto;

public interface RepoGuantesMoto extends JpaRepository<GuantesMoto, Long> {
    
}
