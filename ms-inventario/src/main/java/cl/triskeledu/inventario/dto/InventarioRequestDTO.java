package cl.triskeledu.inventario.dto;

public record InventarioRequestDTO(
        String productoSku,
        Integer tiendaId,
        Integer cantidad
) {
}
