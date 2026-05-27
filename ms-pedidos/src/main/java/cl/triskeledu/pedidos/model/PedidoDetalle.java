package cl.triskeledu.pedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_detalles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name = "producto_sku", nullable = false, length = 50)
    private String productoSku;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer precio;
}
