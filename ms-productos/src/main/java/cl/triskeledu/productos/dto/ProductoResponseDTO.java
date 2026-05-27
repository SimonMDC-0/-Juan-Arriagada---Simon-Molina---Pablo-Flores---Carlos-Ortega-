package cl.triskeledu.productos.dto;

import java.util.List;

public record ProductoResponseDTO(
    String sku,
    String nombre,
    Integer precio,
    String descripcion,
    String nombreCategoria,
    List<String> urlsImagenes
) {}
