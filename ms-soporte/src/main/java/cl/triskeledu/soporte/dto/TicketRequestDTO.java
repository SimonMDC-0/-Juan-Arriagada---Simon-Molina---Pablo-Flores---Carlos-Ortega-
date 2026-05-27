package cl.triskeledu.soporte.dto;

public record TicketRequestDTO(
        Integer usuarioId,
        Integer pedidoId,
        String asunto,
        String descripcion
) {
}
