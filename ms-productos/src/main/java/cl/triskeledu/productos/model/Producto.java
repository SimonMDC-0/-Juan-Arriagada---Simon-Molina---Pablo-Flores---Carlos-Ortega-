package cl.triskeledu.productos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Producto {
    @Id
    @Column(length = 50)
    private String sku;
    
    @Column(nullable = false, length = 150)
    private String nombre;
    
    @Column(nullable = false)
    private Integer precio;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
