package backend.spring.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {
    private Long id;
    private LocalDate fecha;
    private Float total;
    private Long clienteId;
    private Estado estado;
    private String direccion;
    private Long telefono;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.fecha = pedido.getFecha();
        this.total = pedido.getTotal();
        this.clienteId = pedido.getCliente().getId();
        this.estado = pedido.getEstado();
        if (pedido.getDireccion() != null) {
            this.direccion = String.format("%s, %s",pedido.getDireccion().getNombre(), pedido.getDireccion().getNumero());
        }
        if (pedido.getTelefono() != null) {
            this.telefono = pedido.getTelefono().getNumero();
        }
            
    }

    public static List<PedidoDTO> toListDTO(List<Pedido> listPedido) {
        List<PedidoDTO> dtoList = new ArrayList<>();

        for (Pedido lPedido : listPedido) {
            dtoList.add(new PedidoDTO(lPedido));
        }
        
        return dtoList;
    }
}
