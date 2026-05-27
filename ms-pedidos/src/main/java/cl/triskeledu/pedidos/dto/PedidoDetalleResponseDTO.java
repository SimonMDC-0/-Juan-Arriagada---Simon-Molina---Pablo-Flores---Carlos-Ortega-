package cl.triskeledu.pedidos.dto;

public record PedidoDetalleResponseDTO(
        Integer id,
        String productoSku,
        Integer cantidad,
        Integer precio
) {
}
