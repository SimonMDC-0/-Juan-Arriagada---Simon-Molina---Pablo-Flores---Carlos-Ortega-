package cl.triskeledu.soporte.dto;

import java.time.LocalDateTime;

public record TicketResponseDTO(
        Integer id,
        Integer usuarioId,
        Integer pedidoId,
        String asunto,
        String descripcion,
        String estado,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion
) {
}
