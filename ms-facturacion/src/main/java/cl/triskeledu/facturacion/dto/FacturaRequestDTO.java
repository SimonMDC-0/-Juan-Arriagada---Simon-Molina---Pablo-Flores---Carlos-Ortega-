package cl.triskeledu.facturacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FacturaRequestDTO(
        @NotNull(message = "El campo pedidoId no puede ser nulo")
        Integer pedidoId,
        @NotNull(message = "El campo usuarioId no puede ser nulo")
        Integer usuarioId,
        @NotNull(message = "El campo subtotal no puede ser nulo")
        Integer subtotal,
        @NotNull(message = "El campo impuestos no puede ser nulo")
        Integer impuestos
) {
}
