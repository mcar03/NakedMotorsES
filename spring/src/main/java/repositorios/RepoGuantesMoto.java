package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import modelos.GuantesMoto;

public interface RepoGuantesMoto extends JpaRepository<GuantesMoto, Long> {
    
}
