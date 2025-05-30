package serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelos.Usuario;
import repositorios.RepoUsuario;
import servicios.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private RepoUsuario repoUsuario;

    @Override
    public List<Usuario> findAll() {
        // TODO Auto-generated method stub
        return repoUsuario.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        // TODO Auto-generated method stub
        return repoUsuario.findById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        // TODO Auto-generated method stub
        return repoUsuario.findByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        // TODO Auto-generated method stub
        return repoUsuario.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repoUsuario.deleteById(id);
    }
    
}
