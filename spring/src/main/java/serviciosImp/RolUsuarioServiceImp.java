package serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import modelos.RolUsuario;
import repositorios.RepoRolUsuario;
import servicios.RolUsuarioService;

public class RolUsuarioServiceImp implements RolUsuarioService {

    @Autowired
    private RepoRolUsuario repoRolUsuario;

    @Override
    public List<RolUsuario> findAll() {
        // TODO Auto-generated method stub
        return repoRolUsuario.findAll();
    }

    @Override
    public Optional<RolUsuario> findById(Long id) {
        // TODO Auto-generated method stub
       return repoRolUsuario.findById(id);
    }

    @Override
    public RolUsuario save(RolUsuario rolUsuario) {
        // TODO Auto-generated method stub
       return repoRolUsuario.save(rolUsuario);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repoRolUsuario.deleteById(id);
    }
    
}
