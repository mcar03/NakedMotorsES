package backend.spring.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class GuantesMoto extends Producto {
    
    @Enumerated(EnumType.STRING)
    private TipoTallas talla;
    @Enumerated(EnumType.STRING)
    private MaterialTraje material;
    
}
