package cl.triskeledu.carrito.repository;

import cl.triskeledu.carrito.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
}
