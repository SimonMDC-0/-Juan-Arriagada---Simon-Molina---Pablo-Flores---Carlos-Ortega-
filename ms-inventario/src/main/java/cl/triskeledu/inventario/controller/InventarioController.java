package cl.triskeledu.inventario.controller;

import cl.triskeledu.inventario.dto.InventarioRequestDTO;
import cl.triskeledu.inventario.dto.InventarioResponseDTO;
import cl.triskeledu.inventario.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping("/ingreso")
    public ResponseEntity<InventarioResponseDTO> registrarIngreso(@RequestBody InventarioRequestDTO request) {
        return new ResponseEntity<>(inventarioService.registrarIngreso(request), HttpStatus.CREATED);
    }

    @PostMapping("/salida")
    public ResponseEntity<InventarioResponseDTO> registrarSalida(@RequestBody InventarioRequestDTO request) {
        return ResponseEntity.ok(inventarioService.registrarSalida(request));
    }

    @GetMapping("/producto/{productoSku}")
    public ResponseEntity<List<InventarioResponseDTO>> obtenerPorProducto(@PathVariable String productoSku) {
        return ResponseEntity.ok(inventarioService.obtenerInventarioPorProducto(productoSku));
    }

    @GetMapping("/tienda/{tiendaId}")
    public ResponseEntity<List<InventarioResponseDTO>> obtenerPorTienda(@PathVariable Integer tiendaId) {
        return ResponseEntity.ok(inventarioService.obtenerInventarioPorTienda(tiendaId));
    }

    @GetMapping("/producto/{productoSku}/tienda/{tiendaId}")
    public ResponseEntity<InventarioResponseDTO> obtenerStockEspecifico(@PathVariable String productoSku, @PathVariable Integer tiendaId) {
        return ResponseEntity.ok(inventarioService.obtenerStockEspecifico(productoSku, tiendaId));
    }
}
