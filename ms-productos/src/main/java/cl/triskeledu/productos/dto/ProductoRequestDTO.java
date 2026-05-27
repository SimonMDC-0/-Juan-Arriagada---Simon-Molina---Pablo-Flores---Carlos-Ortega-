package cl.triskeledu.productos.dto;

public record ProductoRequestDTO(
    String sku,
    String nombre,
    Integer precio,
    String descripcion,
    String nombreCategoria
) {}
