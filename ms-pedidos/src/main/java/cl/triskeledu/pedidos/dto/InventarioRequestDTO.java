package cl.triskeledu.pedidos.dto;

public record InventarioRequestDTO(
        String productoSku,
        Integer tiendaId,
        Integer cantidad
) {}
