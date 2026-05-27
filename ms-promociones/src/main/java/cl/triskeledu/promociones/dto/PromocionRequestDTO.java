package cl.triskeledu.promociones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PromocionRequestDTO(
        @NotBlank(message = "El campo codigo es obligatorio")
        String codigo,
        @NotBlank(message = "El campo descripcion es obligatorio")
        String descripcion,
        @NotNull(message = "El campo porcentajeDescuento no puede ser nulo")
        Integer porcentajeDescuento,
        @NotNull(message = "El campo fechaInicio no puede ser nulo")
        LocalDateTime fechaInicio,
        @NotNull(message = "El campo fechaFin no puede ser nulo")
        LocalDateTime fechaFin,
        @NotNull(message = "El campo activa no puede ser nulo")
        Boolean activa
) {
}
