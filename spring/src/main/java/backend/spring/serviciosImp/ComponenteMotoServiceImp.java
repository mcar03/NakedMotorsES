package backend.spring.serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.spring.modelos.ComponenteMoto;
import backend.spring.repositorios.RepoComponenteMoto;
import backend.spring.servicios.ComponenteMotoService;

@Service
public class ComponenteMotoServiceImp implements ComponenteMotoService {

    @Autowired
    private RepoComponenteMoto repoComponenteMoto;

    @Override
    public List<ComponenteMoto> findAll() {
        // TODO Auto-generated method stub
        return repoComponenteMoto.findAll();
    }

    @Override
    public Optional<ComponenteMoto> findById(Long id) {
        // TODO Auto-generated method stub
        return repoComponenteMoto.findById(id);
    }

    @Override
    public ComponenteMoto save(ComponenteMoto componenteMoto) {
        // TODO Auto-generated method stub
        return repoComponenteMoto.save(componenteMoto);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repoComponenteMoto.deleteById(id);
    }
    
}
