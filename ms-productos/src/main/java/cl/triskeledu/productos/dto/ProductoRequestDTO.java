package cl.triskeledu.productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoRequestDTO(
    @NotBlank(message = "El campo sku es obligatorio")
    String sku,
    @NotBlank(message = "El campo nombre es obligatorio")
    String nombre,
    @NotNull(message = "El campo precio no puede ser nulo")
    Integer precio,
    @NotBlank(message = "El campo descripcion es obligatorio")
    String descripcion,
    @NotBlank(message = "El campo nombreCategoria es obligatorio")
    String nombreCategoria
) {}
