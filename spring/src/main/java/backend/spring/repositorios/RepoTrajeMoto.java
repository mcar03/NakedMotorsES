package backend.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.spring.modelos.TrajeMoto;

public interface RepoTrajeMoto extends JpaRepository<TrajeMoto, Long> {
    
}
