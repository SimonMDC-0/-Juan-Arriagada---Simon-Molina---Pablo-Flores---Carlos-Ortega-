package cl.triskeledu.pedidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoDetalleRequestDTO(
        @NotBlank(message = "El campo productoSku es obligatorio")
        String productoSku,
        @NotNull(message = "El campo cantidad no puede ser nulo")
        Integer cantidad,
        @NotNull(message = "El campo precio no puede ser nulo")
        Integer precio
) {
}
