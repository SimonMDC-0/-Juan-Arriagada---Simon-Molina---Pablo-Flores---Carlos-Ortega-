package cl.triskeledu.soporte.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.soporte.dto.TicketRequestDTO;
import cl.triskeledu.soporte.dto.TicketResponseDTO;
import cl.triskeledu.soporte.exceptions.BadRequestException;
import cl.triskeledu.soporte.exceptions.ResourceNotFoundException;
import cl.triskeledu.soporte.model.Ticket;
import cl.triskeledu.soporte.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public TicketResponseDTO crearTicket(TicketRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        Ticket ticket = new Ticket(
                null,
                request.usuarioId(),
                request.pedidoId(),
                request.asunto(),
                request.descripcion(),
                "ABIERTO",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Ticket saved = ticketRepository.save(ticket);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponseDTO obtenerTicketPorId(Integer id) {
        log.info("Ejecutando lógica de servicio");
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket " + id + " no encontrado"));
        return mapToResponse(ticket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponseDTO> listarTicketsPorUsuario(Integer usuarioId) {
        log.info("Ejecutando lógica de servicio");
        return ticketRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TicketResponseDTO actualizarEstadoTicket(Integer id, String nuevoEstado) {
        log.info("Ejecutando lógica de servicio");
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket " + id + " no encontrado"));

        ticket.setEstado(nuevoEstado);
        ticket.setFechaActualizacion(LocalDateTime.now());
        
        return mapToResponse(ticketRepository.save(ticket));
    }

    private TicketResponseDTO mapToResponse(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getUsuarioId(),
                ticket.getPedidoId(),
                ticket.getAsunto(),
                ticket.getDescripcion(),
                ticket.getEstado(),
                ticket.getFechaCreacion(),
                ticket.getFechaActualizacion()
        );
    }
}
