package backend.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.Categoria;

@Repository
public interface RepoCategoria extends JpaRepository<Categoria, Long> {
    //@Query("SELECT c FROM Categoria c WHERE c.padre = :padre")
    List<Categoria> findByPadre(Categoria padre);
}
