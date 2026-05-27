package cl.triskeledu.productos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagenes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "producto_sku", nullable = false, length = 50)
    private String productoSku;
    
    @Column(name = "url_imagen", nullable = false, length = 255)
    private String urlImagen;
    
    @Column(name = "es_principal")
    private Boolean esPrincipal = false;
}
