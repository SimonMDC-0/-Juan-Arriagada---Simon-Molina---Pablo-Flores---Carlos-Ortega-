package cl.triskeledu.pedidos.repository;

import cl.triskeledu.pedidos.model.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Integer> {
}
