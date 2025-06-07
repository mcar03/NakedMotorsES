package backend.spring.servicios;


import java.util.List;

import org.springframework.stereotype.Service;

import backend.spring.modelos.Producto;
import backend.spring.modelos.ProductoLike;
import backend.spring.modelos.Usuario;
import backend.spring.repositorios.RepoProductoLike;


@Service
public class ProductoLikeService {

    private final RepoProductoLike likeRepository;

    public ProductoLikeService(RepoProductoLike likeRepository) {
        this.likeRepository = likeRepository;
    }

    public boolean toggleLike(Usuario usuario, Producto producto) {
        return likeRepository.findByUsuarioAndProducto(usuario, producto)
            .map(like -> {
                likeRepository.delete(like);
                return false; // ahora no le gusta
            })
            .orElseGet(() -> {
                ProductoLike nuevoLike = new ProductoLike();
                nuevoLike.setUsuario(usuario);
                nuevoLike.setProducto(producto);
                likeRepository.save(nuevoLike);
                return true; // ahora le gusta
            });
    }

    public List<ProductoLike> getLikesByUsuario(Usuario usuario) {
        return likeRepository.findAllByUsuario(usuario);
    }

    public boolean isProductoLikedByUsuario(Usuario usuario, Producto producto) {
        return likeRepository.findByUsuarioAndProducto(usuario, producto).isPresent();
    }
}

