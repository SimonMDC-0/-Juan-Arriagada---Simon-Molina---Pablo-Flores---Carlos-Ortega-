package cl.triskeledu.usuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "direcciones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "usuario_email", nullable = false, length = 150)
    private String usuarioEmail;
    
    @Column(nullable = false, length = 255)
    private String calle;
    
    @Column(nullable = false, length = 100)
    private String ciudad;
    
    @Column(nullable = false, length = 100)
    private String region;
}
