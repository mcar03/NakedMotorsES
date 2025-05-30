package controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modelos.LineaPedido;
import servicios.LineaPedidoService;

@RestController
@RequestMapping("/lineas-pedido")
public class LineaPedidoController {
    
    @Autowired
    private LineaPedidoService lineaPedidoService;

    @GetMapping
    public List<LineaPedido> getAll() {
        return lineaPedidoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LineaPedido> getById(@PathVariable Long id) {
        return lineaPedidoService.findById(id);
    }

    @PostMapping
    public LineaPedido create(@RequestBody LineaPedido lineaPedido) {
        return lineaPedidoService.save(lineaPedido);
    }

    @PutMapping("/{id}")
    public LineaPedido update(@PathVariable Long id, @RequestBody LineaPedido lineaPedido) {
        lineaPedido.setId(id);
        return lineaPedidoService.save(lineaPedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        lineaPedidoService.deleteById(id);
    }
}
