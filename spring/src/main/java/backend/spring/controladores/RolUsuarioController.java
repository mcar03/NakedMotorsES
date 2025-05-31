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

import backend.spring.modelos.RolUsuario;
import backend.spring.servicios.RolUsuarioService;

@RestController
@RequestMapping("/roles-usuario")
public class RolUsuarioController {
    
    @Autowired
    private RolUsuarioService rolUsuarioService;

    @GetMapping
    public List<RolUsuario> getAll() {
        return rolUsuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<RolUsuario> getById(@PathVariable Long id) {
        return rolUsuarioService.findById(id);
    }

    @PostMapping
    public RolUsuario create(@RequestBody RolUsuario rolUsuario) {
        return rolUsuarioService.save(rolUsuario);
    }

    @PutMapping("/{id}")
    public RolUsuario update(@PathVariable Long id, @RequestBody RolUsuario rolUsuario) {
        rolUsuario.setId(id);
        return rolUsuarioService.save(rolUsuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rolUsuarioService.deleteById(id);
    }
}
