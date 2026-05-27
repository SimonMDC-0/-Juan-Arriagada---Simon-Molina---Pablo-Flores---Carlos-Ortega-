package cl.triskeledu.pedidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PedidoRequestDTO(
        @NotNull(message = "El campo usuarioId no puede ser nulo")
        Integer usuarioId,
        @NotNull(message = "El campo carritoId no puede ser nulo")
        Integer carritoId,
        @NotNull(message = "El campo detalles no puede ser nulo")
        List<PedidoDetalleRequestDTO> detalles
) {
}
