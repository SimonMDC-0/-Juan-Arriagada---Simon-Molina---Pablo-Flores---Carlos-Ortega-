package cl.triskeledu.carrito.dto;

public record CarritoItemResponseDTO(
        Integer id,
        String productoSku,
        Integer cantidad,
        Integer precioUnitario
) {
}
