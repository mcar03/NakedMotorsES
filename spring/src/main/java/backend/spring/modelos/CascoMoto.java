package backend.spring.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CascoMoto extends Producto {

    @Enumerated(EnumType.STRING)
    private TipoTallas talla;
    @Enumerated(EnumType.STRING)
    private TipoCascoMoto tipoCasco;
    private String color;
    private boolean homologado;
    private String material;
    @Enumerated(EnumType.STRING)
    private MarcasCascosMoto marcaCasco;
    
}