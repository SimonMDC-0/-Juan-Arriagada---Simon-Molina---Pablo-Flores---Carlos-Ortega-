package cl.triskeledu.tiendas.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.tiendas.dto.TiendaRequestDTO;
import cl.triskeledu.tiendas.dto.TiendaResponseDTO;
import cl.triskeledu.tiendas.service.TiendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    private final TiendaService tiendaService;

    public TiendaController(TiendaService tiendaService) {
        this.tiendaService = tiendaService;
    }

    @PostMapping
    public ResponseEntity<TiendaResponseDTO> crearTienda(@Valid @RequestBody TiendaRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        return new ResponseEntity<>(tiendaService.crearTienda(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiendaResponseDTO> obtenerTienda(@PathVariable Integer id) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(tiendaService.obtenerTiendaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TiendaResponseDTO>> listarTiendas() {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(tiendaService.listarTiendas());
    }
}
