package serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelos.CascoMoto;
import repositorios.RepoCascoMoto;
import servicios.CascoMotoService;

@Service
public class CascoMotoServiceImp implements CascoMotoService {

    @Autowired
    private RepoCascoMoto repoCascoMoto;

    @Override
    public List<CascoMoto> findAll() {
        // TODO Auto-generated method stub
        return repoCascoMoto.findAll();
    }

    @Override
    public Optional<CascoMoto> findById(Long id) {
        // TODO Auto-generated method stub
        return repoCascoMoto.findById(id);
    }

    @Override
    public CascoMoto save(CascoMoto cascoMoto) {
        // TODO Auto-generated method stub
        return repoCascoMoto.save(cascoMoto);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repoCascoMoto.deleteById(id);
    }
    
}
