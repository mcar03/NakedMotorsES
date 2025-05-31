package backend.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.spring.modelos.LineaPedido;

public interface RepoLineaPedido extends JpaRepository<LineaPedido, Long> {
    
}
