package backend.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.Categoria;
import backend.spring.modelos.Producto;


@Repository
public interface RepoProducto extends JpaRepository<Producto, Long> {
    //@Query("SELECT p FROM Producto p WHERE p.categoria = :padre")
    List<Producto> findByCategoria(Categoria padre);

}