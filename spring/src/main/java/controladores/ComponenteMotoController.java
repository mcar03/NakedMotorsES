package controladores;

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

import modelos.ComponenteMoto;
import servicios.ComponenteMotoService;

@RestController
@RequestMapping("/componentes-moto")
public class ComponenteMotoController {

    @Autowired
    private ComponenteMotoService componenteMotoService;

    @GetMapping
    public List<ComponenteMoto> getAll() {
        return componenteMotoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ComponenteMoto> getById(@PathVariable Long id) {
        return componenteMotoService.findById(id);
    }

    @PostMapping
    public ComponenteMoto create(@RequestBody ComponenteMoto componenteMoto) {
        return componenteMotoService.save(componenteMoto);
    }

    @PutMapping("/{id}")
    public ComponenteMoto update(@PathVariable Long id, @RequestBody ComponenteMoto componenteMoto) {
        componenteMoto.setId(id);
        return componenteMotoService.save(componenteMoto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        componenteMotoService.deleteById(id);
    }
}
