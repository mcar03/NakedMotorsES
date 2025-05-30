package modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TrajeMoto extends Producto {
    
    @Enumerated(EnumType.STRING)
    private TipoMarcaTraje marcaTraje;
    @Enumerated(EnumType.STRING)
    private TipoTallas tallasTraje;
    private String color;
    @Enumerated(EnumType.STRING)
    private MaterialTraje material;
   
}
