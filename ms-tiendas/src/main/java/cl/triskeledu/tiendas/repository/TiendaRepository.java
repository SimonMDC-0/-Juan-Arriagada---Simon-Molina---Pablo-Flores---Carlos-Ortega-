package cl.triskeledu.tiendas.repository;

import cl.triskeledu.tiendas.model.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiendaRepository extends JpaRepository<Tienda, Integer> {
}
