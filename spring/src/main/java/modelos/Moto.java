package modelos;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data 
@NoArgsConstructor
public class Moto extends Producto {
    
    private TipoMoto tipoMoto;
    private MarcaMoto marcaMoto;
    private String modelo;
    private String annoFabricacion;
    private int cv;


}
