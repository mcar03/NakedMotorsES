package backend.spring.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import backend.spring.modelos.CodigoPostal;
import backend.spring.repositorios.RepoCodigoPostal;

import java.util.List;

@RestController
@RequestMapping("/codpos")
public class CodigoPostalController {

    @Autowired
    private RepoCodigoPostal repoCodigoPostal;

    @GetMapping("/{cp}")
    public ResponseEntity<CodigoPostal> findByCodigoPostal(@PathVariable("cp") Integer cp) {
        List<CodigoPostal> resultados = repoCodigoPostal.findByCodigoPostal(cp);

        if (!resultados.isEmpty()) {
            return ResponseEntity.ok(resultados.get(0));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
