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

import backend.spring.modelos.TrajeMoto;
import backend.spring.servicios.TrajeMotoService;

@RestController
@RequestMapping("/trajes-moto")
public class TrajeMotoController {
    
    @Autowired
    private TrajeMotoService trajeMotoService;

    @GetMapping
    public List<TrajeMoto> getAll() {
        return trajeMotoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TrajeMoto> getById(@PathVariable Long id) {
        return trajeMotoService.findById(id);
    }

    @PostMapping
    public TrajeMoto create(@RequestBody TrajeMoto trajeMoto) {
        return trajeMotoService.save(trajeMoto);
    }

    @PutMapping("/{id}")
    public TrajeMoto update(@PathVariable Long id, @RequestBody TrajeMoto trajeMoto) {
        trajeMoto.setId(id);
        return trajeMotoService.save(trajeMoto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trajeMotoService.deleteById(id);
    }
}
