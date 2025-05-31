package backend.spring.serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.spring.modelos.Categoria;
import backend.spring.repositorios.RepoCategoria;
import backend.spring.servicios.CategoriaService;

@Service
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    private RepoCategoria repoCategoria;

    @Override
    public List<Categoria> findAll() {
        // TODO Auto-generated method stub
         return repoCategoria.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        // TODO Auto-generated method stub
         return repoCategoria.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        // TODO Auto-generated method stub
         return repoCategoria.save(categoria);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        repoCategoria.deleteById(id);
    }
    
}
