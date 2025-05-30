package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import modelos.TrajeMoto;

public interface RepoTrajeMoto extends JpaRepository<TrajeMoto, Long> {
    
}
