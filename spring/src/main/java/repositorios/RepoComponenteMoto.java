package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modelos.ComponenteMoto;


public interface RepoComponenteMoto extends JpaRepository<ComponenteMoto, Long> {
    
}
