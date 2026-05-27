package cl.triskeledu.carrito.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarritoItemRequestDTO(
        @NotNull(message = "El campo carritoId no puede ser nulo")
        Integer carritoId,
        @NotBlank(message = "El campo productoSku es obligatorio")
        String productoSku,
        @NotNull(message = "El campo cantidad no puede ser nulo")
        Integer cantidad,
        @NotNull(message = "El campo precioUnitario no puede ser nulo")
        Integer precioUnitario
) {
}
