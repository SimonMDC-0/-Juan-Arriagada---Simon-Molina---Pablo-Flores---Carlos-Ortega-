package cl.triskeledu.promociones.dto;

import java.time.LocalDateTime;

public record PromocionRequestDTO(
        String codigo,
        String descripcion,
        Integer porcentajeDescuento,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        Boolean activa
) {
}
