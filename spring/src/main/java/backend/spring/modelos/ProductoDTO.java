package backend.spring.modelos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String talla;
    private Float precio;
    private int stock;
    private String imagenurl;
    private Long categoriaId;
    private String categoriaNombre;

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.talla = producto.getTalla();
        this.precio = producto.getPrecio();
        this.stock = producto.getStock();
        this.imagenurl = producto.getImagenurl();
        if(producto.getCategoria() != null) {
            this.categoriaId = producto.getCategoria().getId();
            this.categoriaNombre = producto.getCategoria().getNombre();
        }
    }
    
}
