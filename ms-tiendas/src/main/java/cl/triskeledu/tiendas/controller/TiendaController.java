package cl.triskeledu.tiendas.controller;

import cl.triskeledu.tiendas.dto.TiendaRequestDTO;
import cl.triskeledu.tiendas.dto.TiendaResponseDTO;
import cl.triskeledu.tiendas.service.TiendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    private final TiendaService tiendaService;

    public TiendaController(TiendaService tiendaService) {
        this.tiendaService = tiendaService;
    }

    @PostMapping
    public ResponseEntity<TiendaResponseDTO> crearTienda(@RequestBody TiendaRequestDTO request) {
        return new ResponseEntity<>(tiendaService.crearTienda(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiendaResponseDTO> obtenerTienda(@PathVariable Integer id) {
        return ResponseEntity.ok(tiendaService.obtenerTiendaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TiendaResponseDTO>> listarTiendas() {
        return ResponseEntity.ok(tiendaService.listarTiendas());
    }
}
