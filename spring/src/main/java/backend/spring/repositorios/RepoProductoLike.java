package backend.spring.repositorios;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.spring.modelos.Producto;
import backend.spring.modelos.ProductoLike;
import backend.spring.modelos.Usuario;

public interface RepoProductoLike extends JpaRepository<ProductoLike, Long> {
    
    Optional<ProductoLike> findByUsuarioAndProducto(Usuario usuario, Producto producto);

    List<ProductoLike> findAllByUsuario(Usuario usuario);

    void deleteByUsuarioAndProducto(Usuario usuario, Producto producto);
}

