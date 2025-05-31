package backend.spring.serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.spring.modelos.GuantesMoto;
import backend.spring.repositorios.RepoGuantesMoto;
import backend.spring.servicios.GuantesMotoService;

@Service
public class GuantesMotoServiceImp implements GuantesMotoService {

    @Autowired
    private RepoGuantesMoto repoGuantesMoto;

    @Override
    public List<GuantesMoto> findAll() {
        // TODO Auto-generated method stub
        return repoGuantesMoto.findAll();
    }

    @Override
    public Optional<GuantesMoto> findById(Long id) {
        // TODO Auto-generated method stub
         return repoGuantesMoto.findById(id);
    }

    @Override
    public GuantesMoto save(GuantesMoto guantesMoto) {
        // TODO Auto-generated method stub
        return repoGuantesMoto.save(guantesMoto);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repoGuantesMoto.deleteById(id);
    }
    
}
