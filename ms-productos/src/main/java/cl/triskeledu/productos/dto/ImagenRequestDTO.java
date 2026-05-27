package cl.triskeledu.productos.dto;

public record ImagenRequestDTO(
    String productoSku,
    String urlImagen,
    Boolean esPrincipal
) {}
