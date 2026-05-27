package cl.triskeledu.inventario.dto;

public record InventarioResponseDTO(
        Integer id,
        String productoSku,
        Integer tiendaId,
        Integer cantidad
) {
}
