package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import modelos.LineaPedido;

public interface RepoLineaPedido extends JpaRepository<LineaPedido, Long> {
    
}
