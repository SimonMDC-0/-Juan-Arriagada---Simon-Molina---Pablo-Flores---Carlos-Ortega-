package cl.triskeledu.carrito.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CarritoResponseDTO(
        Integer id,
        Integer usuarioId,
        LocalDateTime fechaCreacion,
        String estado,
        List<CarritoItemResponseDTO> items
) {
}
