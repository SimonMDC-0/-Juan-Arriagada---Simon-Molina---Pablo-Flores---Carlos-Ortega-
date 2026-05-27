package cl.triskeledu.productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImagenRequestDTO(
    @NotBlank(message = "El campo productoSku es obligatorio")
    String productoSku,
    @NotBlank(message = "El campo urlImagen es obligatorio")
    String urlImagen,
    @NotNull(message = "El campo esPrincipal no puede ser nulo")
    Boolean esPrincipal
) {}
