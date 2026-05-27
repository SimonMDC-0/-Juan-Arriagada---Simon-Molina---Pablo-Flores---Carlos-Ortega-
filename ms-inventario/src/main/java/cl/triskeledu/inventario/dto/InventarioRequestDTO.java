package cl.triskeledu.inventario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InventarioRequestDTO(
        @NotBlank(message = "El campo productoSku es obligatorio")
        String productoSku,
        @NotNull(message = "El campo tiendaId no puede ser nulo")
        Integer tiendaId,
        @NotNull(message = "El campo cantidad no puede ser nulo")
        Integer cantidad
) {
}
