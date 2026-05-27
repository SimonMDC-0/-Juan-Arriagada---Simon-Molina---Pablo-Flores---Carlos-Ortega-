package cl.triskeledu.productos.repository;

import cl.triskeledu.productos.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImagenRepository extends JpaRepository<Imagen, Integer> {
    List<Imagen> findByProductoSku(String productoSku);
}
