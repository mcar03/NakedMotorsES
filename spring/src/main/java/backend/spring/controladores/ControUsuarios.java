package backend.spring.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import backend.spring.modelos.Direccion;
import backend.spring.modelos.Rol;
import backend.spring.modelos.RolUsuario;
import backend.spring.modelos.Telefono;
import backend.spring.modelos.Usuario;
import backend.spring.repositorios.RepoDireccion;
import backend.spring.repositorios.RepoRolUsuario;
import backend.spring.repositorios.RepoTelefono;
import backend.spring.repositorios.RepoUsuario;

@RestController
@RequestMapping("/api/usuarios")
public class ControUsuarios {

    @Autowired
    private RepoUsuario repoUsuario;

    @Autowired
    private RepoTelefono repoTelefono;

    @Autowired
    private RepoDireccion repoDireccion;

    @Autowired
    private RepoRolUsuario repoRolUsuario;

    @GetMapping("")
    public List<Usuario> getAllUsuarios() {
        return repoUsuario.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return repoUsuario.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        if (usuario.getPassword() != null && usuario.getPassword().length() >= 2) {
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        }
        Usuario savedUsuario = repoUsuario.save(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> existing = repoUsuario.findById(id);
        if (!existing.isPresent()) return ResponseEntity.notFound().build();

        usuario.setId(id);
        if (usuario.getPassword() != null && usuario.getPassword().length() >= 2) {
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        } else {
            usuario.setPassword(existing.get().getPassword());
        }

        return ResponseEntity.ok(repoUsuario.save(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (!repoUsuario.existsById(id)) return ResponseEntity.notFound().build();
        repoUsuario.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<Void> setRolesToUsuario(@PathVariable Long id, @RequestBody List<String> roles) {
        Optional<Usuario> usuarioOpt = repoUsuario.findById(id);
        if (!usuarioOpt.isPresent()) return ResponseEntity.notFound().build();
        Usuario usuario = usuarioOpt.get();
        repoRolUsuario.deleteAll(repoRolUsuario.findByUsuario(usuario));
        for (String rol : roles) {
            RolUsuario ru = new RolUsuario();
            ru.setUsuario(usuario);
            ru.setRol(Rol.valueOf(rol));
            repoRolUsuario.save(ru);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/telefonos")
    public ResponseEntity<List<Telefono>> getTelefonosByUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = repoUsuario.findById(id);
        return usuarioOpt.map(usuario -> ResponseEntity.ok(usuario.getTelefonos()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/telefonos")
    public ResponseEntity<Telefono> addTelefonoToUsuario(@PathVariable Long id, @RequestBody Telefono telefono) {
        Optional<Usuario> usuarioOpt = repoUsuario.findById(id);
        if (!usuarioOpt.isPresent()) return ResponseEntity.notFound().build();
        telefono.setUsuario(usuarioOpt.get());
        return new ResponseEntity<>(repoTelefono.save(telefono), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUser}/telefonos/{idTel}")
    public ResponseEntity<Void> deleteTelefonoFromUsuario(@PathVariable Long idUser, @PathVariable Long idTel) {
        Optional<Telefono> telOpt = repoTelefono.findById(idTel);
        if (!telOpt.isPresent() || !telOpt.get().getUsuario().getId().equals(idUser)) {
            return ResponseEntity.notFound().build();
        }
        repoTelefono.delete(telOpt.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/direcciones")
    public ResponseEntity<List<Direccion>> getDireccionesByUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = repoUsuario.findById(id);
        return usuarioOpt.map(usuario -> ResponseEntity.ok(usuario.getDirecciones()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/direcciones")
    public ResponseEntity<Direccion> addDireccionToUsuario(@PathVariable Long id, @RequestBody Direccion direccion) {
        Optional<Usuario> usuarioOpt = repoUsuario.findById(id);
        if (!usuarioOpt.isPresent()) return ResponseEntity.notFound().build();
        direccion.setUsuario(usuarioOpt.get());
        return new ResponseEntity<>(repoDireccion.save(direccion), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUser}/direcciones/{idDir}")
    public ResponseEntity<Void> deleteDireccionFromUsuario(@PathVariable Long idUser, @PathVariable Long idDir) {
        Optional<Direccion> dirOpt = repoDireccion.findById(idDir);
        if (!dirOpt.isPresent() || !dirOpt.get().getUsuario().getId().equals(idUser)) {
            return ResponseEntity.notFound().build();
        }
        repoDireccion.delete(dirOpt.get());
        return ResponseEntity.noContent().build();
    }
}
