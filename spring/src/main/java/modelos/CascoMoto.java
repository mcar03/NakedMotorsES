package modelos;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CascoMoto extends Producto {

    private TipoTallas talla;
    private TipoCascoMoto tipoCasco;
    private String color;
    private boolean homologado;
    private String material;
    private MarcasCascosMoto marcaCasco;
    
}