package backend.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.spring.modelos.Moto;

public interface RepoMoto extends JpaRepository<Moto, Long> {
    
}
