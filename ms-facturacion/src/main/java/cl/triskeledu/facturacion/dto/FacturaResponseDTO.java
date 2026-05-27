package cl.triskeledu.facturacion.dto;

import java.time.LocalDateTime;

public record FacturaResponseDTO(
        Integer id,
        Integer pedidoId,
        LocalDateTime fecha,
        Integer subtotal,
        Integer impuestos,
        Integer total,
        String rutCliente,
        String razonSocial
) {
}
