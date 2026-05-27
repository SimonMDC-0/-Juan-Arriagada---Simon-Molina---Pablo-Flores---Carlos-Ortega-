package cl.triskeledu.productos.repository;

import cl.triskeledu.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, String> {
    List<Producto> findByCategoriaId(Integer categoriaId);
}
