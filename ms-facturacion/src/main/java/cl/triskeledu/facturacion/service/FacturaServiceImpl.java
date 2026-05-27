package cl.triskeledu.facturacion.service;

import cl.triskeledu.facturacion.dto.FacturaRequestDTO;
import cl.triskeledu.facturacion.dto.FacturaResponseDTO;
import cl.triskeledu.facturacion.exceptions.BadRequestException;
import cl.triskeledu.facturacion.exceptions.ResourceNotFoundException;
import cl.triskeledu.facturacion.model.Factura;
import cl.triskeledu.facturacion.repository.FacturaRepository;
import cl.triskeledu.facturacion.client.UsuarioClient;
import cl.triskeledu.facturacion.dto.UsuarioDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final UsuarioClient usuarioClient;

    public FacturaServiceImpl(FacturaRepository facturaRepository, UsuarioClient usuarioClient) {
        this.facturaRepository = facturaRepository;
        this.usuarioClient = usuarioClient;
    }

    @Override
    @Transactional
    public FacturaResponseDTO emitirFactura(FacturaRequestDTO request) {
        if (facturaRepository.findByPedidoId(request.pedidoId()).isPresent()) {
            throw new BadRequestException("Ya existe una factura emitida para el pedido " + request.pedidoId());
        }

        if (request.subtotal() < 0 || request.impuestos() < 0) {
            throw new BadRequestException("Valores de subtotal o impuestos no válidos");
        }

        UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorId(request.usuarioId());
        if (usuario == null) {
            throw new ResourceNotFoundException("El usuario con ID " + request.usuarioId() + " no existe");
        }

        Integer total = request.subtotal() + request.impuestos();

        Factura factura = new Factura(
                null,
                request.pedidoId(),
                LocalDateTime.now(),
                request.subtotal(),
                request.impuestos(),
                total,
                usuario.rut(),
                usuario.nombre()
        );

        Factura saved = facturaRepository.save(factura);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FacturaResponseDTO obtenerFacturaPorPedido(Integer pedidoId) {
        Factura factura = facturaRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Factura para el pedido " + pedidoId + " no encontrada"));
        return mapToResponse(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaResponseDTO> listarFacturas() {
        return facturaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private FacturaResponseDTO mapToResponse(Factura factura) {
        return new FacturaResponseDTO(
                factura.getId(),
                factura.getPedidoId(),
                factura.getFecha(),
                factura.getSubtotal(),
                factura.getImpuestos(),
                factura.getTotal(),
                factura.getRutCliente(),
                factura.getRazonSocial()
        );
    }
}
