package backend.spring.controladores;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import backend.spring.excepciones.CarroException;
import backend.spring.modelos.CantidadDTO;
import backend.spring.modelos.Estado;
import backend.spring.modelos.LineaPedido;
import backend.spring.modelos.LineaPedidoDTO;
import backend.spring.modelos.Pedido;
import backend.spring.modelos.PedidoDTO;
import backend.spring.modelos.Producto;
import backend.spring.modelos.Usuario;
import backend.spring.repositorios.*;

@RestController
@RequestMapping("/api/carro")
public class CarritoControllerRest {

    @Autowired private RepoProducto repoProducto;
    @Autowired private RepoPedido repoPedido;
    @Autowired private RepoUsuario repoUsuario;
    @Autowired private RepoLineaPedido repoLineaPedido;

    private Usuario getLoggedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    return repoUsuario.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
}

    @GetMapping("")
    public List<LineaPedidoDTO> getCarro() {
        Usuario cliente = getLoggedUser();
        List<Pedido> pedidos = repoPedido.findByEstadoAndCliente(Estado.CARRITO, cliente);
        return pedidos.isEmpty() ? List.of() : LineaPedidoDTO.toListDTO(repoLineaPedido.findByPedido(pedidos.get(0)));
    }

    @PostMapping("/add/{id}")
    public LineaPedidoDTO addProducto(@PathVariable Long id, @Valid @RequestBody CantidadDTO cantidadDto) {
        Usuario cliente = getLoggedUser();
        Producto producto = repoProducto.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        int cantidad = cantidadDto.getCantidad();

        //if (cantidad <= 0) throw new RuntimeException("Cantidad inválida");

        Pedido carrito = repoPedido.findByEstadoAndCliente(Estado.CARRITO, cliente)
                .stream().findFirst().orElseGet(() -> {
                    Pedido nuevo = new Pedido();
                    nuevo.setCliente(cliente);
                    nuevo.setEstado(Estado.CARRITO);
                    return repoPedido.save(nuevo);
                });

        LineaPedido linea = carrito.getLineaPedidos().stream()
                .filter(lp -> lp.getProducto().getId().equals(id))
                .findFirst()
                .orElse(new LineaPedido());

        int nuevaCantidad = linea.getId() != null ? linea.getCantidad() + cantidad : cantidad;

        if (nuevaCantidad > producto.getStock()) {
            throw new RuntimeException("Stock insuficiente");
        }

        linea.setProducto(producto);
        linea.setCantidad(nuevaCantidad);
        linea.setPedido(carrito);
        linea.setPrecio(producto.getPrecio());
        linea.setImagenurl(producto.getImagenurl());

        LineaPedido newPedido = repoLineaPedido.save(linea);
        return new LineaPedidoDTO(newPedido);
    }

    @PutMapping("/edit/{id}")
    public LineaPedidoDTO editarCantidad(@PathVariable Long id, @Valid @RequestBody CantidadDTO cantidadDto) {
        int cantidad = cantidadDto.getCantidad();
        Usuario cliente = getLoggedUser();
        LineaPedido linea = repoLineaPedido.findById(id)
                .orElseThrow(() -> new RuntimeException("LineaPedido no encontrada"));

        if (!repoLineaPedido.lineaPedidoBelongsToUser(linea, cliente).isEmpty()
                && linea.getProducto().getStock() >= cantidad) {
            linea.setCantidad(cantidad);
            return new LineaPedidoDTO(repoLineaPedido.save(linea));
        } else {
            throw new RuntimeException("Stock insuficiente o no autorizado");
        }
    }

    @DeleteMapping("/del/{id}")
    public void borrarLinea(@PathVariable Long id) {
        Usuario cliente = getLoggedUser();
        LineaPedido linea = repoLineaPedido.findById(id)
                .orElseThrow(() -> new RuntimeException("LineaPedido no encontrada"));

        if (!repoLineaPedido.lineaPedidoBelongsToUser(linea, cliente).isEmpty()) {
            Pedido carro = linea.getPedido();
            repoLineaPedido.delete(linea);
            if (carro.getLineaPedidos().isEmpty()) {
                repoPedido.delete(carro);
            }
        } else {
            throw new RuntimeException("No autorizado para borrar esta línea");
        }
    }

    @PostMapping("/confirmar/{pedidoId}")
    @Transactional(rollbackOn = CarroException.class)
    public PedidoDTO confirmarPedido(@PathVariable Long pedidoId) throws CarroException {
        Usuario cliente = getLoggedUser();
        Pedido pedido = repoPedido.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (!pedido.getCliente().equals(cliente) || pedido.getEstado() != Estado.CARRITO)
            throw new RuntimeException("No autorizado o pedido ya confirmado");

        float total = 0;

        for (LineaPedido lp : pedido.getLineaPedidos()) {
            Producto producto = lp.getProducto();
            if (producto.getStock() >= lp.getCantidad()) {
                lp.setPrecio(producto.getPrecio());
                total += lp.getCantidad() * producto.getPrecio();
                producto.setStock(producto.getStock() - lp.getCantidad());
                repoProducto.save(producto);
            } else {
                throw new CarroException("No hay stock suficiente de " + producto.getNombre());
            }
        }

        pedido.setEstado(Estado.REALIZADO);
        pedido.setFecha(LocalDate.now());
        pedido.setTotal(total);
        pedido.setDireccion(cliente.getDirecciones().get(0));
        pedido.setTelefono(cliente.getTelefonos().get(0));
        return new PedidoDTO(repoPedido.save(pedido));
    }

    @GetMapping("/pedidos")
    public List<PedidoDTO> getPedidosRealizados() {
        List<Pedido> pedidos = repoPedido.findByCliente(getLoggedUser());
        pedidos.removeIf(p -> p.getEstado() == Estado.CARRITO);
        return PedidoDTO.toListDTO(pedidos);
    }

    @GetMapping("/pedidos/{id}")
    public PedidoDTO detallePedido(@PathVariable Long id) {
        Pedido pedido = repoPedido.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        if (!pedido.getCliente().equals(getLoggedUser())) {
            throw new RuntimeException("No autorizado para ver este pedido");
        }
        return new PedidoDTO(pedido);
    }
}
