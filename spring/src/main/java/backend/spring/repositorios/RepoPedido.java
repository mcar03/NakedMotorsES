package backend.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.spring.modelos.Pedido;

public interface RepoPedido extends JpaRepository<Pedido, Long> {
    
}
