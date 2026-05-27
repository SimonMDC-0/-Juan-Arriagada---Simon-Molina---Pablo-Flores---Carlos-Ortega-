package cl.triskeledu.soporte.repository;

import cl.triskeledu.soporte.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByUsuarioId(Integer usuarioId);
}
