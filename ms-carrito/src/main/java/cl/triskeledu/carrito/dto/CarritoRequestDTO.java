package cl.triskeledu.carrito.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarritoRequestDTO(
        @NotNull(message = "El campo usuarioId no puede ser nulo")
        Integer usuarioId
) {
}
