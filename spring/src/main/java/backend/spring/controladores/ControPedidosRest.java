package backend.spring.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import backend.spring.modelos.Estado;
import backend.spring.modelos.Pedido;
import backend.spring.modelos.PedidoDTO;
import backend.spring.modelos.Usuario;
import backend.spring.repositorios.RepoPedido;
import backend.spring.repositorios.RepoUsuario;

@RestController
@RequestMapping("/api/pedidos")
public class ControPedidosRest {
    
    @Autowired
    private RepoPedido repoPedido;

    @Autowired
    private RepoUsuario repoUsuario;

    private Usuario getLoggedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    return repoUsuario.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @GetMapping("")
    public List<PedidoDTO> getPedidosRealizados() {
        return PedidoDTO.toListDTO(repoPedido.findByEstado(Estado.REALIZADO));
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getDetallePedido(@PathVariable Long id) {
        Optional<Pedido> oPedido = repoPedido.findById(id);
        if (oPedido.isPresent()) {
            return ResponseEntity.ok(new PedidoDTO(oPedido.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Pedido no encontrado");
        }
    }
}
