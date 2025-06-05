package backend.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import backend.spring.modelos.Rol;
import backend.spring.modelos.RolUsuario;
import backend.spring.modelos.Telefono;
import backend.spring.modelos.Usuario;
import backend.spring.repositorios.RepoRolUsuario;
import backend.spring.repositorios.RepoTelefono;
import backend.spring.repositorios.RepoUsuario;

@RestController
@RequestMapping("/api") // prefijo común para api REST
public class ControGeneralRest {

    @Autowired
    private RepoTelefono repoTelefono;

    @Autowired
    private RepoUsuario repoUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RepoRolUsuario repoRolUsuario;

    // Para endpoints de vistas no es habitual en REST, así que las elimino o deberías servirlas con otro controlador @Controller

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @NonNull @RequestParam String username,
        @NonNull @RequestParam String password,
        @NonNull @RequestParam String nombre,
        @NonNull @RequestParam String apellidos,
        @NonNull @RequestParam Long pais,
        @NonNull @RequestParam Long telefono,
        @NonNull @RequestParam String email) {        

        try {
            if (!repoUsuario.findByUsername(username).isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El username ya existe");
            }
            if (!repoUsuario.findByEmail(email).isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El email ya está registrado");
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellido(apellidos);
            usuario.setUsername(username);
            usuario.setEmail(email);
            usuario.setPassword(passwordEncoder.encode(password));
            usuario.setEnabled(true);
            usuario = repoUsuario.save(usuario);
            
            Telefono tel = new Telefono();
            tel.setNumero(telefono);
            tel.setCodigoPais(pais);
            tel.setUsuario(usuario);
            repoTelefono.save(tel);

            RolUsuario rolUsuario = new RolUsuario();
            rolUsuario.setRol(Rol.CLIENTE);
            rolUsuario.setUsuario(usuario);
            repoRolUsuario.save(rolUsuario);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario " + username + " creado correctamente");

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear usuario: " + e.getMessage());
        }
    }
}
