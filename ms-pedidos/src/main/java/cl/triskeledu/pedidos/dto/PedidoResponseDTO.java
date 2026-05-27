package cl.triskeledu.pedidos.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Integer id,
        Integer usuarioId,
        Integer carritoId,
        LocalDateTime fecha,
        Integer total,
        String estado,
        List<PedidoDetalleResponseDTO> detalles
) {
}
