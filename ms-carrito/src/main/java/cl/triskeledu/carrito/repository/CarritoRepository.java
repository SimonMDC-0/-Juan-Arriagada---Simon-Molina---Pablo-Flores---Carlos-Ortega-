package cl.triskeledu.carrito.repository;

import cl.triskeledu.carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByUsuarioIdAndEstado(Integer usuarioId, String estado);
}
