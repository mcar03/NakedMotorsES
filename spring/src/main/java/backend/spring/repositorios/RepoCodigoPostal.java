package backend.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.spring.modelos.CodigoPostal;



@Repository
public interface RepoCodigoPostal extends JpaRepository <CodigoPostal, Long> {
    List<CodigoPostal> findByCodigoPostal(Integer codigoPostal);
}