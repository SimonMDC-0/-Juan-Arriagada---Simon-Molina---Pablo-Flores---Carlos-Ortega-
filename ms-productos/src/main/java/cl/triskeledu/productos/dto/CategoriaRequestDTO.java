package cl.triskeledu.productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDTO(
    @NotBlank(message = "El campo nombreCategoria es obligatorio")
    String nombreCategoria,
    @NotBlank(message = "El campo descripcion es obligatorio")
    String descripcion
) {}
