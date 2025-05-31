package backend.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.ComponenteMoto;


public interface RepoComponenteMoto extends JpaRepository<ComponenteMoto, Long> {
    
}
