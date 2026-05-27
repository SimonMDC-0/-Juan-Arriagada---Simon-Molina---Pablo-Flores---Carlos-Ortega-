package cl.triskeledu.logistica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnvioRequestDTO(
        @NotNull(message = "El campo pedidoId no puede ser nulo")
        Integer pedidoId,
        @NotBlank(message = "El campo direccion es obligatorio")
        String direccion
) {
}
