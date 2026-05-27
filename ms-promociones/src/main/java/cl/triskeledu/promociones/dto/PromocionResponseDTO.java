package cl.triskeledu.promociones.dto;

import java.time.LocalDateTime;

public record PromocionResponseDTO(
        Integer id,
        String codigo,
        String descripcion,
        Integer porcentajeDescuento,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        Boolean activa
) {
}
