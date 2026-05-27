package cl.triskeledu.usuarios.repository;

import cl.triskeledu.usuarios.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    List<Direccion> findByUsuarioEmail(String usuarioEmail);
}
