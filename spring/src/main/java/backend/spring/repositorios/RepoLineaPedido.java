package backend.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.LineaPedido;
import backend.spring.modelos.Pedido;
import backend.spring.modelos.Usuario;

@Repository
public interface RepoLineaPedido extends JpaRepository<LineaPedido, Long> {
    
    List<LineaPedido> findByPedido(Pedido pedido);


    @Query("SELECT lp " +
       "FROM LineaPedido lp " +
       "JOIN Pedido pedido on lp.pedido = pedido " +
       "WHERE lp = :lineaPedido " +
       "AND pedido.cliente = :usuario")
    List<LineaPedido> lineaPedidoBelongsToUser(LineaPedido lineaPedido, Usuario usuario);
    
}
