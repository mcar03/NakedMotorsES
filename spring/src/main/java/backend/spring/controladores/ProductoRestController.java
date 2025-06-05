package backend.spring.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.spring.modelos.Producto;
import backend.spring.repositorios.RepoProducto;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @Autowired
    private RepoProducto repoProducto;

    // GET /api/productos
    @GetMapping
    public List<Producto> listarProductos() {
        return repoProducto.findAll();
    }

    // GET /api/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        Optional<Producto> producto = repoProducto.findById(id);
        return producto.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/productos
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return repoProducto.save(producto);
    }

    // PUT /api/productos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto producto) {
        if (!repoProducto.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        return ResponseEntity.ok(repoProducto.save(producto));
    }

    // DELETE /api/productos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!repoProducto.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repoProducto.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
