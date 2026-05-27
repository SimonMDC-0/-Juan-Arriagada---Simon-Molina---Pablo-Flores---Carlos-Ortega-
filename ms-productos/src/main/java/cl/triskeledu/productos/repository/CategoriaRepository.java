package cl.triskeledu.productos.repository;

import cl.triskeledu.productos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByNombreCategoria(String nombreCategoria);
}
