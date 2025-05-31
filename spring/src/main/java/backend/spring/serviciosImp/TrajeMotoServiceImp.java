package backend.spring.serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.spring.modelos.TrajeMoto;
import backend.spring.repositorios.RepoTrajeMoto;
import backend.spring.servicios.TrajeMotoService;

@Service
public class TrajeMotoServiceImp implements TrajeMotoService {

    @Autowired
    private RepoTrajeMoto repoTrajeMoto;

    @Override
    public List<TrajeMoto> findAll() {
        // TODO Auto-generated method stub
        return repoTrajeMoto.findAll();
    }

    @Override
    public Optional<TrajeMoto> findById(Long id) {
        // TODO Auto-generated method stub
        return repoTrajeMoto.findById(id);
    }

    @Override
    public TrajeMoto save(TrajeMoto trajeMoto) {
        // TODO Auto-generated method stub
        return repoTrajeMoto.save(trajeMoto);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repoTrajeMoto.deleteById(id);
    }
    
}
