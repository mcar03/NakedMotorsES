package servicios;

import java.util.List;
import java.util.Optional;

import modelos.LineaPedido;

public interface LineaPedidoService {
    List<LineaPedido> findAll();
    Optional<LineaPedido> findById(Long id);
    LineaPedido save(LineaPedido lineaPedido);
    void deleteById(Long id);
}
