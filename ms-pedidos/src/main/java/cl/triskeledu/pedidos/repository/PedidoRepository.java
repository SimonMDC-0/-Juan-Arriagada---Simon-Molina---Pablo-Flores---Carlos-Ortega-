package cl.triskeledu.pedidos.repository;

import cl.triskeledu.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUsuarioId(Integer usuarioId);
}
