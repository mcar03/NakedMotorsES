package backend.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.spring.modelos.ProductoDTO;
import backend.spring.repositorios.RepoCategoria;
import backend.spring.repositorios.RepoProducto;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @Autowired
    private RepoProducto repoProducto;

    @GetMapping
    public List<ProductoDTO> listarProductos() {
        return repoProducto.findAll()
                .stream()
                .map(ProductoDTO::new)
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!repoProducto.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repoProducto.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}