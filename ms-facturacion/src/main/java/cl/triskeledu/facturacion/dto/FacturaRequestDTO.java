package cl.triskeledu.facturacion.dto;

public record FacturaRequestDTO(
        Integer pedidoId,
        Integer usuarioId,
        Integer subtotal,
        Integer impuestos
) {
}
