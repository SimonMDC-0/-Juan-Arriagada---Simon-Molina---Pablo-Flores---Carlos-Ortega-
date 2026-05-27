package cl.triskeledu.tiendas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TiendaRequestDTO(
        @NotBlank(message = "El campo nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El campo direccion es obligatorio")
        String direccion,
        @NotBlank(message = "El campo telefono es obligatorio")
        String telefono,
        @NotBlank(message = "El campo horario es obligatorio")
        String horario
) {
}
