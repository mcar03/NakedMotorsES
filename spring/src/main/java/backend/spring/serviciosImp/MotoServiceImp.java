package backend.spring.serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.spring.modelos.Moto;
import backend.spring.repositorios.RepoMoto;
import backend.spring.servicios.MotoService;

@Service
public class MotoServiceImp implements MotoService {

    @Autowired
    private RepoMoto repoMoto;

    @Override
    public List<Moto> findAll() {
        // TODO Auto-generated method stub
        return repoMoto.findAll();
    }

    @Override
    public Optional<Moto> findById(Long id) {
        // TODO Auto-generated method stub
        return repoMoto.findById(id);
    }

    @Override
    public Moto save(Moto moto) {
        // TODO Auto-generated method stub
        return repoMoto.save(moto);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repoMoto.deleteById(id);
    }
    
}
