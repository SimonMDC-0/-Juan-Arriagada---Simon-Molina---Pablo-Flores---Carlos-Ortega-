package cl.triskeledu.pedidos.service;

import cl.triskeledu.pedidos.dto.PedidoDetalleResponseDTO;
import cl.triskeledu.pedidos.dto.PedidoRequestDTO;
import cl.triskeledu.pedidos.dto.PedidoResponseDTO;
import cl.triskeledu.pedidos.exceptions.BadRequestException;
import cl.triskeledu.pedidos.exceptions.ResourceNotFoundException;
import cl.triskeledu.pedidos.model.Pedido;
import cl.triskeledu.pedidos.model.PedidoDetalle;
import cl.triskeledu.pedidos.repository.PedidoRepository;
import cl.triskeledu.pedidos.client.CarritoClient;
import cl.triskeledu.pedidos.client.InventarioClient;
import cl.triskeledu.pedidos.dto.CarritoResponseDTO;
import cl.triskeledu.pedidos.dto.InventarioRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CarritoClient carritoClient;
    private final InventarioClient inventarioClient;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, CarritoClient carritoClient, InventarioClient inventarioClient) {
        this.pedidoRepository = pedidoRepository;
        this.carritoClient = carritoClient;
        this.inventarioClient = inventarioClient;
    }

    @Override
    @Transactional
    public PedidoResponseDTO crearPedido(PedidoRequestDTO request) {
        CarritoResponseDTO carrito = carritoClient.obtenerCarritoActivo(request.usuarioId());
        
        if (carrito.items() == null || carrito.items().isEmpty()) {
            throw new BadRequestException("El carrito está vacío, no se puede crear un pedido");
        }

        Pedido pedido = new Pedido(
                null,
                request.usuarioId(),
                carrito.id(),
                LocalDateTime.now(),
                0,
                "CREADO",
                new ArrayList<>()
        );

        int total = 0;
        for (var item : carrito.items()) {
            PedidoDetalle detalle = new PedidoDetalle(null, pedido, item.productoSku(), item.cantidad(), item.precioUnitario());
            pedido.getDetalles().add(detalle);
            total += (item.cantidad() * item.precioUnitario());
            
            // Descontar inventario (Asumiendo Tienda Central ID=1 por defecto para la demo)
            inventarioClient.registrarSalida(new InventarioRequestDTO(item.productoSku(), 1, item.cantidad()));
        }

        pedido.setTotal(total);
        Pedido saved = pedidoRepository.save(pedido);

        // Vaciar el carrito tras crear el pedido exitosamente
        carritoClient.vaciarCarrito(carrito.id());

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoResponseDTO obtenerPedidoPorId(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido " + id + " no encontrado"));
        return mapToResponse(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarPedidosPorUsuario(Integer usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoResponseDTO actualizarEstadoPedido(Integer id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido " + id + " no encontrado"));

        pedido.setEstado(nuevoEstado);
        return mapToResponse(pedidoRepository.save(pedido));
    }

    private PedidoResponseDTO mapToResponse(Pedido pedido) {
        List<PedidoDetalleResponseDTO> detallesDto = pedido.getDetalles().stream()
                .map(d -> new PedidoDetalleResponseDTO(d.getId(), d.getProductoSku(), d.getCantidad(), d.getPrecio()))
                .collect(Collectors.toList());

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getUsuarioId(),
                pedido.getCarritoId(),
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.getEstado(),
                detallesDto
        );
    }
}
