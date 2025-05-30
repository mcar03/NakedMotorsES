package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import modelos.Moto;

public interface RepoMoto extends JpaRepository<Moto, Long> {
    
}
