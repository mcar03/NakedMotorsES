package backend.spring.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.spring.modelos.Usuario;
import backend.spring.repositorios.RepoUsuario;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private final RepoUsuario repoUsuario;

    public UsuarioServiceImp(RepoUsuario repoUsuario) {
        this.repoUsuario = repoUsuario;
    }

    @Override
    public List<Usuario> findAll() {
        return repoUsuario.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repoUsuario.findById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repoUsuario.findByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repoUsuario.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        repoUsuario.deleteById(id);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return repoUsuario.findByUsername(username);
    }
}
