package backend.spring.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.spring.jwt.JwtUtil;
import backend.spring.modelos.LoginRequest;
import backend.spring.modelos.LoginResponse;
import backend.spring.modelos.RegisterRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication;
        try {
            System.out.printf("%s username, %s password\n", request.getUsername(), request.getPassword());
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtil.generateTokenFromUsername(userDetails);

        List<String> roles = new ArrayList<>();

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.add(authority.getAuthority());
        }

        LoginResponse response = new LoginResponse(jwtToken, userDetails.getUsername(), roles);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String username = request.getUsername();
        String rawPassword = request.getPassword();
        String email = request.getEmail();
        String nombre = request.getNombre();
    
        if (username == null || username.isBlank() || rawPassword == null || rawPassword.isBlank()
                || email == null || email.isBlank() || nombre == null || nombre.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", false,
                "message", "Todos los campos son obligatorios"
            ));
        }
    
        // Comprobar si ya existe el usuario
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM usuario WHERE username = ?",
            Integer.class, username
        );
        if (count != null && count > 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "status", false,
                "message", "El nombre de usuario ya está en uso"
            ));
        }
    
        // Insertar usuario en tabla 'usuario'
        String encodedPassword = passwordEncoder.encode(rawPassword);
        jdbcTemplate.update(
            "INSERT INTO usuario (username, password, email, nombre, enabled) VALUES (?, ?, ?, ?, true)",
            username, encodedPassword, email, nombre
        );
    
        // Obtener ID del nuevo usuario
        Integer userId = jdbcTemplate.queryForObject(
            "SELECT id FROM usuario WHERE username = ?",
            Integer.class, username
        );
    
        // Insertar rol CLIENTE en tabla 'rol_usuario'
        jdbcTemplate.update(
            "INSERT INTO rol_usuario (usuario_id, rol) VALUES (?, ?)",
            userId, "CLIENTE"
        );
    
        return ResponseEntity.ok(Map.of(
            "status", true,
            "message", "Usuario registrado correctamente"
        ));
    }
}
