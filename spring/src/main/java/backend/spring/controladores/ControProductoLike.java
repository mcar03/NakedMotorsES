package backend.spring.controladores;


import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import backend.spring.modelos.Producto;
import backend.spring.modelos.ProductoDTO;
import backend.spring.modelos.Usuario;
import backend.spring.servicios.ProductoLikeService;
import backend.spring.servicios.ProductoService;
import backend.spring.servicios.UsuarioService;

@RestController
@RequestMapping("/api/likes")
public class ControProductoLike {

    private final ProductoLikeService likeService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    public ControProductoLike(ProductoLikeService likeService, UsuarioService usuarioService, ProductoService productoService) {
        this.likeService = likeService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    // Devuelve todos los productos que el usuario ha marcado como like
    @GetMapping
    public List<ProductoDTO> getLikes(Principal principal) {
        Usuario usuario = usuarioService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return likeService.getLikesByUsuario(usuario)
                .stream()
                .map(pl -> new ProductoDTO(pl.getProducto()))
                .collect(Collectors.toList());
    }

    // Marca o desmarca like de un producto para el usuario logueado
    @PostMapping("/{productoId}/toggle")
    public boolean toggleLike(@PathVariable Long productoId, Principal principal) {
        Usuario usuario = usuarioService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Producto producto = productoService.buscarPorId(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return likeService.toggleLike(usuario, producto);
    }
}

