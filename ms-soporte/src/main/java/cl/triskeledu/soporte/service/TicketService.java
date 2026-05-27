package cl.triskeledu.soporte.service;

import cl.triskeledu.soporte.dto.TicketRequestDTO;
import cl.triskeledu.soporte.dto.TicketResponseDTO;

import java.util.List;

public interface TicketService {
    TicketResponseDTO crearTicket(TicketRequestDTO request);
    TicketResponseDTO obtenerTicketPorId(Integer id);
    List<TicketResponseDTO> listarTicketsPorUsuario(Integer usuarioId);
    TicketResponseDTO actualizarEstadoTicket(Integer id, String nuevoEstado);
}
