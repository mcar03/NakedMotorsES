package modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data 
@NoArgsConstructor
public class Moto extends Producto {
    
    @Enumerated(EnumType.STRING)
    private TipoMoto tipoMoto;
    @Enumerated(EnumType.STRING)
    private MarcaMoto marcaMoto;
    private String modelo;
    private String annoFabricacion;
    private int cv;


}
