package cl.triskeledu.promociones.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.promociones.dto.PromocionRequestDTO;
import cl.triskeledu.promociones.dto.PromocionResponseDTO;
import cl.triskeledu.promociones.service.PromocionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/promociones")
public class PromocionController {

    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    @PostMapping
    public ResponseEntity<PromocionResponseDTO> crearPromocion(@Valid @RequestBody PromocionRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        return new ResponseEntity<>(promocionService.crearPromocion(request), HttpStatus.CREATED);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PromocionResponseDTO> obtenerPromocion(@PathVariable String codigo) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(promocionService.obtenerPromocionPorCodigo(codigo));
    }

    @GetMapping
    public ResponseEntity<List<PromocionResponseDTO>> listarPromociones() {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(promocionService.listarPromociones());
    }

    @GetMapping("/{codigo}/aplicar")
    public ResponseEntity<PromocionResponseDTO> aplicarPromocion(@PathVariable String codigo) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(promocionService.aplicarPromocion(codigo));
    }
}
