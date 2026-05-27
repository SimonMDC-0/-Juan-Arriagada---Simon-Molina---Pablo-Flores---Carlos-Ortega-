package cl.triskeledu.pedidos.dto;

public record CarritoItemResponseDTO(
        Integer id,
        String productoSku,
        Integer cantidad,
        Integer precioUnitario
) {}
