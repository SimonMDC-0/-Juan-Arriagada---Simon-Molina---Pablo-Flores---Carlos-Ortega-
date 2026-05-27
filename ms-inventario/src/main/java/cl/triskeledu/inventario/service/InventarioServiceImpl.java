package cl.triskeledu.inventario.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.inventario.dto.InventarioRequestDTO;
import cl.triskeledu.inventario.dto.InventarioResponseDTO;
import cl.triskeledu.inventario.exceptions.BadRequestException;
import cl.triskeledu.inventario.exceptions.ResourceNotFoundException;
import cl.triskeledu.inventario.model.Inventario;
import cl.triskeledu.inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioServiceImpl(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    @Transactional
    public InventarioResponseDTO registrarIngreso(InventarioRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        if (request.cantidad() <= 0) {
            throw new BadRequestException("La cantidad a ingresar debe ser mayor a cero.");
        }

        Optional<Inventario> opt = inventarioRepository.findByProductoSkuAndTiendaId(request.productoSku(), request.tiendaId());
        Inventario inventario;

        if (opt.isPresent()) {
            inventario = opt.get();
            inventario.setCantidad(inventario.getCantidad() + request.cantidad());
        } else {
            inventario = new Inventario(null, request.productoSku(), request.tiendaId(), request.cantidad());
        }

        Inventario saved = inventarioRepository.save(inventario);
        return mapToResponse(saved);
    }

    @Override
    @Transactional
    public InventarioResponseDTO registrarSalida(InventarioRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        if (request.cantidad() <= 0) {
            throw new BadRequestException("La cantidad a descontar debe ser mayor a cero.");
        }

        Inventario inventario = inventarioRepository.findByProductoSkuAndTiendaId(request.productoSku(), request.tiendaId())
                .orElseThrow(() -> new ResourceNotFoundException("No hay inventario registrado para el SKU " + request.productoSku() + " en la tienda " + request.tiendaId()));

        if (inventario.getCantidad() < request.cantidad()) {
            throw new BadRequestException("Stock insuficiente. Stock actual: " + inventario.getCantidad());
        }

        inventario.setCantidad(inventario.getCantidad() - request.cantidad());
        Inventario saved = inventarioRepository.save(inventario);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponseDTO> obtenerInventarioPorProducto(String productoSku) {
        log.info("Ejecutando lógica de servicio");
        return inventarioRepository.findByProductoSku(productoSku).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponseDTO> obtenerInventarioPorTienda(Integer tiendaId) {
        log.info("Ejecutando lógica de servicio");
        return inventarioRepository.findByTiendaId(tiendaId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioResponseDTO obtenerStockEspecifico(String productoSku, Integer tiendaId) {
        log.info("Ejecutando lógica de servicio");
        Inventario inventario = inventarioRepository.findByProductoSkuAndTiendaId(productoSku, tiendaId)
                .orElseThrow(() -> new ResourceNotFoundException("No hay inventario registrado para el SKU " + productoSku + " en la tienda " + tiendaId));
        return mapToResponse(inventario);
    }

    private InventarioResponseDTO mapToResponse(Inventario inventario) {
        return new InventarioResponseDTO(
                inventario.getId(),
                inventario.getProductoSku(),
                inventario.getTiendaId(),
                inventario.getCantidad()
        );
    }
}
