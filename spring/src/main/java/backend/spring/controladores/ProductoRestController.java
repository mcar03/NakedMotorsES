package backend.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}