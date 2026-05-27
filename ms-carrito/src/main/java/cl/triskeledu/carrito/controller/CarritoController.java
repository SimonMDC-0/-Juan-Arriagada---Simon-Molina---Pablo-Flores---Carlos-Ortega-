package cl.triskeledu.carrito.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.carrito.dto.CarritoItemRequestDTO;
import cl.triskeledu.carrito.dto.CarritoRequestDTO;
import cl.triskeledu.carrito.dto.CarritoResponseDTO;
import cl.triskeledu.carrito.service.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping
    public ResponseEntity<CarritoResponseDTO> crearCarrito(@Valid @RequestBody CarritoRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        return new ResponseEntity<>(carritoService.crearCarrito(request), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoResponseDTO> obtenerCarritoActivo(@PathVariable Integer usuarioId) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(carritoService.obtenerCarritoActivo(usuarioId));
    }

    @PostMapping("/items")
    public ResponseEntity<CarritoResponseDTO> agregarItem(@Valid @RequestBody CarritoItemRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        return new ResponseEntity<>(carritoService.agregarItem(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CarritoResponseDTO> removerItem(@PathVariable Integer itemId) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(carritoService.removerItem(itemId));
    }

    @DeleteMapping("/{carritoId}/vaciar")
    public ResponseEntity<CarritoResponseDTO> vaciarCarrito(@PathVariable Integer carritoId) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(carritoService.vaciarCarrito(carritoId));
    }
}
