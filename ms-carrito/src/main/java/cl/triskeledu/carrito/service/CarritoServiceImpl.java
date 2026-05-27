package cl.triskeledu.carrito.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.carrito.dto.CarritoItemRequestDTO;
import cl.triskeledu.carrito.dto.CarritoItemResponseDTO;
import cl.triskeledu.carrito.dto.CarritoRequestDTO;
import cl.triskeledu.carrito.dto.CarritoResponseDTO;
import cl.triskeledu.carrito.exceptions.BadRequestException;
import cl.triskeledu.carrito.exceptions.ResourceNotFoundException;
import cl.triskeledu.carrito.model.Carrito;
import cl.triskeledu.carrito.model.CarritoItem;
import cl.triskeledu.carrito.repository.CarritoItemRepository;
import cl.triskeledu.carrito.repository.CarritoRepository;
import cl.triskeledu.carrito.client.ProductoClient;
import cl.triskeledu.carrito.dto.ProductoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;
    private final ProductoClient productoClient;

    public CarritoServiceImpl(CarritoRepository carritoRepository, CarritoItemRepository carritoItemRepository, ProductoClient productoClient) {
        this.carritoRepository = carritoRepository;
        this.carritoItemRepository = carritoItemRepository;
        this.productoClient = productoClient;
    }

    @Override
    @Transactional
    public CarritoResponseDTO crearCarrito(CarritoRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        Optional<Carrito> activo = carritoRepository.findByUsuarioIdAndEstado(request.usuarioId(), "ACTIVO");
        if (activo.isPresent()) {
            return mapToResponse(activo.get());
        }

        Carrito carrito = new Carrito(null, request.usuarioId(), LocalDateTime.now(), "ACTIVO", new ArrayList<>());
        Carrito saved = carritoRepository.save(carrito);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CarritoResponseDTO obtenerCarritoActivo(Integer usuarioId) {
        log.info("Ejecutando lógica de servicio");
        Carrito carrito = carritoRepository.findByUsuarioIdAndEstado(usuarioId, "ACTIVO")
                .orElseThrow(() -> new ResourceNotFoundException("No hay carrito activo para el usuario " + usuarioId));
        return mapToResponse(carrito);
    }

    @Override
    @Transactional
    public CarritoResponseDTO agregarItem(CarritoItemRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        Carrito carrito = carritoRepository.findById(request.carritoId())
                .orElseThrow(() -> new ResourceNotFoundException("Carrito " + request.carritoId() + " no encontrado"));

        if (!"ACTIVO".equals(carrito.getEstado())) {
            throw new BadRequestException("No se pueden agregar ítems a un carrito que no está ACTIVO");
        }

        if (request.cantidad() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor a 0");
        }

        ProductoDTO productoDTO = productoClient.obtenerProductoPorSku(request.productoSku());
        if (productoDTO == null) {
            throw new ResourceNotFoundException("El producto con SKU " + request.productoSku() + " no existe");
        }
        Integer precioReal = productoDTO.precio();

        Optional<CarritoItem> existingItem = carrito.getItems().stream()
                .filter(i -> i.getProductoSku().equals(request.productoSku()))
                .findFirst();

        if (existingItem.isPresent()) {
            CarritoItem item = existingItem.get();
            item.setCantidad(item.getCantidad() + request.cantidad());
            item.setPrecioUnitario(precioReal);
        } else {
            CarritoItem newItem = new CarritoItem(null, carrito, request.productoSku(), request.cantidad(), precioReal);
            carrito.getItems().add(newItem);
        }

        Carrito saved = carritoRepository.save(carrito);
        return mapToResponse(saved);
    }

    @Override
    @Transactional
    public CarritoResponseDTO removerItem(Integer itemId) {
        log.info("Ejecutando lógica de servicio");
        CarritoItem item = carritoItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item " + itemId + " no encontrado"));

        Carrito carrito = item.getCarrito();
        carrito.getItems().remove(item);
        carritoItemRepository.delete(item);

        return mapToResponse(carritoRepository.save(carrito));
    }

    @Override
    @Transactional
    public CarritoResponseDTO vaciarCarrito(Integer carritoId) {
        log.info("Ejecutando lógica de servicio");
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito " + carritoId + " no encontrado"));

        carrito.getItems().clear();
        return mapToResponse(carritoRepository.save(carrito));
    }

    private CarritoResponseDTO mapToResponse(Carrito carrito) {
        List<CarritoItemResponseDTO> itemsDto = carrito.getItems() != null ? carrito.getItems().stream()
                .map(i -> new CarritoItemResponseDTO(i.getId(), i.getProductoSku(), i.getCantidad(), i.getPrecioUnitario()))
                .collect(Collectors.toList()) : new ArrayList<>();

        return new CarritoResponseDTO(
                carrito.getId(),
                carrito.getUsuarioId(),
                carrito.getFechaCreacion(),
                carrito.getEstado(),
                itemsDto
        );
    }
}
