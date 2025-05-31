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

import backend.spring.modelos.Moto;
import backend.spring.servicios.MotoService;

@RestController
@RequestMapping("/motos")
public class MotoController {
    
    @Autowired
    private MotoService motoService;

    @GetMapping
    public List<Moto> getAll() {
        return motoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Moto> getById(@PathVariable Long id) {
        return motoService.findById(id);
    }

    @PostMapping
    public Moto create(@RequestBody Moto moto) {
        return motoService.save(moto);
    }

    @PutMapping("/{id}")
    public Moto update(@PathVariable Long id, @RequestBody Moto moto) {
        moto.setId(id);
        return motoService.save(moto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        motoService.deleteById(id);
    }
}
