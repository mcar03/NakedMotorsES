package repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import modelos.Pedido;

public interface RepoPedido extends JpaRepository<Pedido, Long> {
    
}
