package cl.triskeledu.facturacion.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.facturacion.dto.FacturaRequestDTO;
import cl.triskeledu.facturacion.dto.FacturaResponseDTO;
import cl.triskeledu.facturacion.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping
    public ResponseEntity<FacturaResponseDTO> emitirFactura(@Valid @RequestBody FacturaRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        return new ResponseEntity<>(facturaService.emitirFactura(request), HttpStatus.CREATED);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<FacturaResponseDTO> obtenerFacturaPorPedido(@PathVariable Integer pedidoId) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(facturaService.obtenerFacturaPorPedido(pedidoId));
    }

    @GetMapping
    public ResponseEntity<List<FacturaResponseDTO>> listarFacturas() {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(facturaService.listarFacturas());
    }
}
