package backend.spring.modelos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LineaPedidoDTO {
    private Long id;
    private int cantidad;
    private Float precio;
    private Long productoId;
    private Long pedidoId;

    public LineaPedidoDTO(LineaPedido lp) {
        this.id = lp.getId();
        this.cantidad = lp.getCantidad();
        this.precio = lp.getPrecio();
        this.productoId = lp.getProducto().getId();
        this.pedidoId = lp.getPedido().getId();
    }

    public static List<LineaPedidoDTO> toListDTO(List<LineaPedido> listLineaPedido) {
        List<LineaPedidoDTO> dtoList = new ArrayList<>();

        for (LineaPedido lPedido : listLineaPedido) {
            dtoList.add(new LineaPedidoDTO(lPedido));
        }
        
        return dtoList;
    }
}
