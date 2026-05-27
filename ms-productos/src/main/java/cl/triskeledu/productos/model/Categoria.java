package cl.triskeledu.productos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombre_categoria", unique = true, nullable = false, length = 100)
    private String nombreCategoria;
    
    @Column(length = 255)
    private String descripcion;
}
