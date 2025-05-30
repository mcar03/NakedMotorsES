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

import modelos.CascoMoto;
import servicios.CascoMotoService;

@RestController
@RequestMapping("/cascos-moto")
public class CascoMotoController {
    
    @Autowired
    private CascoMotoService cascoMotoService;

    @GetMapping
    public List<CascoMoto> getAll() {
        return cascoMotoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CascoMoto> getById(@PathVariable Long id) {
        return cascoMotoService.findById(id);
    }

    @PostMapping
    public CascoMoto create(@RequestBody CascoMoto cascoMoto) {
        return cascoMotoService.save(cascoMoto);
    }

    @PutMapping("/{id}")
    public CascoMoto update(@PathVariable Long id, @RequestBody CascoMoto cascoMoto) {
        cascoMoto.setId(id);
        return cascoMotoService.save(cascoMoto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cascoMotoService.deleteById(id);
    }
}
