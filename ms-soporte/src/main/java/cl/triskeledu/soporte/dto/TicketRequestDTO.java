package cl.triskeledu.soporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketRequestDTO(
        @NotNull(message = "El campo usuarioId no puede ser nulo")
        Integer usuarioId,
        @NotNull(message = "El campo pedidoId no puede ser nulo")
        Integer pedidoId,
        @NotBlank(message = "El campo asunto es obligatorio")
        String asunto,
        @NotBlank(message = "El campo descripcion es obligatorio")
        String descripcion
) {
}
