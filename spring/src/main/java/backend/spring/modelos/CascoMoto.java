package backend.spring.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cascomoto")
public class CascoMoto {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private String descripcion;
    private String imagenUrl;
    @Enumerated(EnumType.STRING)
    private TipoTallas talla;
    @Enumerated(EnumType.STRING)
    private TipoCascoMoto tipoCasco;
    private String color;
    private boolean homologado;
    @Enumerated(EnumType.STRING)
    private MaterialCasco material;
    @Enumerated(EnumType.STRING)
    private MarcasCascosMoto marcaCasco;
    
}