package cl.triskeledu.productos.dto;

public record ImagenResponseDTO(
    Integer id,
    String productoSku,
    String urlImagen,
    Boolean esPrincipal
) {}
