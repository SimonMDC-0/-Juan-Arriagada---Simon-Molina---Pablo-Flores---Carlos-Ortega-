package cl.triskeledu.pedidos.dto;

public record PedidoDetalleRequestDTO(
        String productoSku,
        Integer cantidad,
        Integer precio
) {
}
