package cl.triskeledu.carrito.dto;

public record CarritoItemRequestDTO(
        Integer carritoId,
        String productoSku,
        Integer cantidad,
        Integer precioUnitario
) {
}
