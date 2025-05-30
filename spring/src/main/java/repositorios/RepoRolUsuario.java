package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import modelos.RolUsuario;

public interface RepoRolUsuario extends JpaRepository<RolUsuario, Long> {
    
}
