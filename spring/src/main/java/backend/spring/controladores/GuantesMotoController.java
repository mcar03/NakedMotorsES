package backend.spring.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.spring.modelos.GuantesMoto;
import backend.spring.servicios.GuantesMotoService;

@RestController
@RequestMapping("/guantes-moto")
public class GuantesMotoController {
    

    @Autowired
    private GuantesMotoService guantesMotoService;

    @GetMapping
    public List<GuantesMoto> getAll() {
        return guantesMotoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<GuantesMoto> getById(@PathVariable Long id) {
        return guantesMotoService.findById(id);
    }

    @PostMapping
    public GuantesMoto create(@RequestBody GuantesMoto guantesMoto) {
        return guantesMotoService.save(guantesMoto);
    }

    @PutMapping("/{id}")
    public GuantesMoto update(@PathVariable Long id, @RequestBody GuantesMoto guantesMoto) {
        guantesMoto.setId(id);
        return guantesMotoService.save(guantesMoto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        guantesMotoService.deleteById(id);
    }
}
