package cl.triskeledu.usuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id
    @Column(length = 150)
    private String email;
    
    @Column(nullable = false, length = 150)
    private String nombre;
    
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;
    
    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
    
    @Column(length = 50)
    private String estado = "Activo";
}
