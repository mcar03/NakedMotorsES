package modelos;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class GuantesMoto extends Producto {
    
    private TipoTallas talla;
    private MaterialCasco material;
    
}
