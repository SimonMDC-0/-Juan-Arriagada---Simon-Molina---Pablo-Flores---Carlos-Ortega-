package cl.triskeledu.inventario.repository;

import cl.triskeledu.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    Optional<Inventario> findByProductoSkuAndTiendaId(String productoSku, Integer tiendaId);
    List<Inventario> findByProductoSku(String productoSku);
    List<Inventario> findByTiendaId(Integer tiendaId);
}
