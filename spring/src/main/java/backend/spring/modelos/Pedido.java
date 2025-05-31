package backend.spring.modelos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String observaciones;
    private double descuento;
    private double total;
    @ManyToOne
    private Usuario cliente;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;
    @OneToMany(mappedBy = "pedido")
    private List<LineaPedido> lineaPedidos;
    @ManyToOne
    private Usuario operario;
}
