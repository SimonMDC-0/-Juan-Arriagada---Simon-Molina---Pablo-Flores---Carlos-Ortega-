package cl.triskeledu.logistica.dto;

import java.time.LocalDateTime;

public record EnvioResponseDTO(
        Integer id,
        Integer pedidoId,
        String direccion,
        String estado,
        LocalDateTime fechaDespacho,
        LocalDateTime fechaEntrega
) {
}
