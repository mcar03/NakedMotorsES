package backend.spring.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.spring.modelos.CantidadDTO;
import backend.spring.modelos.Producto;
import backend.spring.modelos.ProductoDTO;
import backend.spring.repositorios.RepoProducto;
import jakarta.validation.Valid;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!repoProducto.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repoProducto.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/delete-stock/{id}")
    public ResponseEntity<Object> quitarStock(@PathVariable Long id, @Valid @RequestBody CantidadDTO cantidad) {
        Map<String, Object> body = new HashMap<>();
        Optional<Producto> producto = repoProducto.findById(id);
        if (!producto.isPresent()) {
            body.put("status", "error");
            body.put("message", "El producto no existe");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
        }

        if (producto.get().getStock() < cantidad.getCantidad()) {
            body.put("status", "error");
            body.put("message", "La cantidad a retirar es mayor al stock actual");
            return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
        }

        Producto newStockProducto = producto.get();

        newStockProducto.setStock(newStockProducto.getStock() - cantidad.getCantidad());

        repoProducto.save(newStockProducto);

        body.put("status", "ok");
        body.put("message", "Stock cambiado");
        return new ResponseEntity<Object>(body, HttpStatus.OK);
    }
}