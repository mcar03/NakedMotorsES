package serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import modelos.Pedido;
import repositorios.RepoPedido;
import servicios.PedidoService;

public class PedidoServiceImp implements PedidoService {

    @Autowired
    private RepoPedido repoPedido;
    @Override
    public List<Pedido> findAll() {
        // TODO Auto-generated method stub
        return repoPedido.findAll();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        // TODO Auto-generated method stub
        return repoPedido.findById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        // TODO Auto-generated method stub
        return repoPedido.save(pedido);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repoPedido.deleteById(id);
    }
    
}
