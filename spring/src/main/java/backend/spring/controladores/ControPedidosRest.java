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
        return repoUsuario.findByUsername(username).get(0);
    }

    @GetMapping("")
    public List<Pedido> getPedidosRealizados() {
        return repoPedido.findByEstado(Estado.REALIZADO);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getDetallePedido(@PathVariable Long id) {
        Optional<Pedido> oPedido = repoPedido.findById(id);
        if (oPedido.isPresent()) {
            return ResponseEntity.ok(oPedido.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Pedido no encontrado");
        }
    }

    @PostMapping("/preparar/{id}")
    public ResponseEntity<?> setPedidoAsPreparando(@PathVariable Long id) {
        Optional<Pedido> oPedido = repoPedido.findById(id);
        if (oPedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }
        Pedido pedido = oPedido.get();
        pedido.setEstado(Estado.PREPARANDO);
        pedido.setOperario(getLoggedUser());
        repoPedido.save(pedido);
        return ResponseEntity.ok("Pedido marcado como PREPARANDO");
    }

    @GetMapping("/en-preparacion")
    public List<Pedido> findPedidosEnPreparacion() {
        Usuario usuario = getLoggedUser();
        return repoPedido.findByEstadoAndOperario(Estado.PREPARANDO, usuario);
    }

    @GetMapping("/en-preparacion/{id}")
    public ResponseEntity<?> prepararPedido(@PathVariable Long id) {
        Optional<Pedido> oPedido = repoPedido.findById(id);
        if (oPedido.isPresent()) {
            return ResponseEntity.ok(oPedido.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Pedido no encontrado");
        }
    }

    @PostMapping("/en-preparacion/{id}")
    public ResponseEntity<?> setPedidoAsEnviado(@PathVariable Long id) {
        Optional<Pedido> oPedido = repoPedido.findById(id);
        if (oPedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }
        Pedido pedido = oPedido.get();
        pedido.setEstado(Estado.ENVIADO);
        pedido.setOperario(getLoggedUser());
        repoPedido.save(pedido);
        return ResponseEntity.ok("Pedido marcado como ENVIADO");
    }

    @GetMapping("/enviados")
    public List<Pedido> findPedidosEnviados() {
        Usuario usuario = getLoggedUser();
        return repoPedido.findByEstadoAndOperario(Estado.ENVIADO, usuario);
    }

    @GetMapping("/mis-pedidos")
    public List<Pedido> findMyPedidos() {
        Usuario usuario = getLoggedUser();
        return repoPedido.findByOperario(usuario);
    }
}
