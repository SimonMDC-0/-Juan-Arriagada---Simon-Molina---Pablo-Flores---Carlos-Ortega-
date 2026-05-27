package cl.triskeledu.inventario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "producto_sku", nullable = false, length = 50)
    private String productoSku;

    @Column(name = "tienda_id", nullable = false)
    private Integer tiendaId;

    @Column(nullable = false)
    private Integer cantidad;
}
