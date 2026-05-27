package cl.triskeledu.carrito.dto;

public record ProductoDTO(
        String sku,
        String nombre,
        Integer precio,
        String descripcion
) {
}
