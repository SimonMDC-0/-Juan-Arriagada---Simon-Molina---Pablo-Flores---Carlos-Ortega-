package cl.triskeledu.facturacion.service;

import cl.triskeledu.facturacion.dto.FacturaRequestDTO;
import cl.triskeledu.facturacion.dto.FacturaResponseDTO;

import java.util.List;

public interface FacturaService {
    FacturaResponseDTO emitirFactura(FacturaRequestDTO request);
    FacturaResponseDTO obtenerFacturaPorPedido(Integer pedidoId);
    List<FacturaResponseDTO> listarFacturas();
}
