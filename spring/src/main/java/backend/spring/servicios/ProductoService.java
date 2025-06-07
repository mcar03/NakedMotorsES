package backend.spring.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.spring.modelos.Categoria;
import backend.spring.modelos.Producto;
import backend.spring.repositorios.RepoProducto;

@Service
public class ProductoService {

    private final RepoProducto repoProducto;

    public ProductoService(RepoProducto repoProducto) {
        this.repoProducto = repoProducto;
    }

    public List<Producto> listarTodos() {
        return repoProducto.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return repoProducto.findById(id);
    }

    public List<Producto> buscarPorCategoria(Categoria categoria) {
        return repoProducto.findByCategoria(categoria);
    }

    public Producto guardar(Producto producto) {
        return repoProducto.save(producto);
    }

    public void eliminarPorId(Long id) {
        repoProducto.deleteById(id);
    }
}

