package backend.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.spring.modelos.RolUsuario;

public interface RepoRolUsuario extends JpaRepository<RolUsuario, Long> {
    
}
