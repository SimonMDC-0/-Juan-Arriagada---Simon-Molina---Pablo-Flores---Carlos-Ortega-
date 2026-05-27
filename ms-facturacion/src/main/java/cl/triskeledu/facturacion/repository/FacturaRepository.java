package cl.triskeledu.facturacion.repository;

import cl.triskeledu.facturacion.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    Optional<Factura> findByPedidoId(Integer pedidoId);
}
