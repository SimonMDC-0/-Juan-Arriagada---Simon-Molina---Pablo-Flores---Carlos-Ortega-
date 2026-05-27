package cl.triskeledu.facturacion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pedido_id", nullable = false, unique = true)
    private Integer pedidoId;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Integer subtotal;

    @Column(nullable = false)
    private Integer impuestos;

    @Column(nullable = false)
    private Integer total;

    @Column(name = "rut_cliente", length = 20)
    private String rutCliente;

    @Column(name = "razon_social", length = 150)
    private String razonSocial;
}
