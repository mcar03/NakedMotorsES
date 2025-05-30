package servicios;

import java.util.List;
import java.util.Optional;

import modelos.Pedido;

public interface PedidoService {
    List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
    Pedido save(Pedido pedido);
    void deleteById(Long id);
}
