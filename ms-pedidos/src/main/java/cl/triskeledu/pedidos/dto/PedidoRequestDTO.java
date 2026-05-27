package cl.triskeledu.pedidos.dto;

import java.util.List;

public record PedidoRequestDTO(
        Integer usuarioId,
        Integer carritoId,
        List<PedidoDetalleRequestDTO> detalles
) {
}
