package backend.spring.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
public class Localidad {
 
    @Id    
    private Integer cp;
    @Column(length = 50)
    private String nombre;
    @Column(length = 25)
    private String provincia;
}
