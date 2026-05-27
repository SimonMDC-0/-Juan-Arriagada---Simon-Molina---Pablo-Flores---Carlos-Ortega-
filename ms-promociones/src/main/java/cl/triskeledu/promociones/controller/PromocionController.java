package cl.triskeledu.promociones.controller;

import cl.triskeledu.promociones.dto.PromocionRequestDTO;
import cl.triskeledu.promociones.dto.PromocionResponseDTO;
import cl.triskeledu.promociones.service.PromocionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {

    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    @PostMapping
    public ResponseEntity<PromocionResponseDTO> crearPromocion(@RequestBody PromocionRequestDTO request) {
        return new ResponseEntity<>(promocionService.crearPromocion(request), HttpStatus.CREATED);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PromocionResponseDTO> obtenerPromocion(@PathVariable String codigo) {
        return ResponseEntity.ok(promocionService.obtenerPromocionPorCodigo(codigo));
    }

    @GetMapping
    public ResponseEntity<List<PromocionResponseDTO>> listarPromociones() {
        return ResponseEntity.ok(promocionService.listarPromociones());
    }

    @GetMapping("/{codigo}/aplicar")
    public ResponseEntity<PromocionResponseDTO> aplicarPromocion(@PathVariable String codigo) {
        return ResponseEntity.ok(promocionService.aplicarPromocion(codigo));
    }
}
