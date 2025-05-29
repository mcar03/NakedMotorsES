package modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TrajeMoto extends Producto {
    
    private TipoMarcaTraje marcaTraje;
    private TipoTallas tallasTraje;
    private String color;
    private MaterialTraje material;
   
}
