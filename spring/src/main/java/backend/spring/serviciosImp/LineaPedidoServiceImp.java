package backend.spring.serviciosImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.spring.modelos.LineaPedido;
import backend.spring.repositorios.RepoLineaPedido;
import backend.spring.servicios.LineaPedidoService;

@Service
public class LineaPedidoServiceImp implements LineaPedidoService {

    @Autowired
    private RepoLineaPedido repoLineaPedido;

    @Override
    public List<LineaPedido> findAll() {
        return repoLineaPedido.findAll();
    }

    @Override
    public Optional<LineaPedido> findById(Long id) {
        return repoLineaPedido.findById(id);
    }

    @Override
    public LineaPedido save(LineaPedido lineaPedido) {
        return repoLineaPedido.save(lineaPedido);
    }

    @Override
    public void deleteById(Long id) {
        repoLineaPedido.deleteById(id);
    }
}
