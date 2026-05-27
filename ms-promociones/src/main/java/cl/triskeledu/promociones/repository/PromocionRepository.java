package cl.triskeledu.promociones.repository;

import cl.triskeledu.promociones.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
    Optional<Promocion> findByCodigo(String codigo);
}
