package repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modelos.Categoria;


public interface RepoCategoria extends JpaRepository<Categoria, Long> {
        List<Categoria> findByCategoriaPadre(Categoria categoriaPadre);
}
