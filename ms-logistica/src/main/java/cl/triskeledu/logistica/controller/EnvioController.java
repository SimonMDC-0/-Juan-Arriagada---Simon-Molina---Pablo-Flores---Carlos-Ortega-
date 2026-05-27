package cl.triskeledu.logistica.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.logistica.dto.EnvioRequestDTO;
import cl.triskeledu.logistica.dto.EnvioResponseDTO;
import cl.triskeledu.logistica.service.EnvioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<EnvioResponseDTO> programarEnvio(@Valid @RequestBody EnvioRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        return new ResponseEntity<>(envioService.programarEnvio(request), HttpStatus.CREATED);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<EnvioResponseDTO> obtenerEnvio(@PathVariable Integer pedidoId) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(envioService.obtenerEnvioPorPedido(pedidoId));
    }

    @GetMapping
    public ResponseEntity<List<EnvioResponseDTO>> listarEnvios() {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(envioService.listarEnvios());
    }

    @PatchMapping("/{id}/despachar")
    public ResponseEntity<EnvioResponseDTO> despacharEnvio(@PathVariable Integer id) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(envioService.despacharEnvio(id));
    }

    @PatchMapping("/{id}/entregar")
    public ResponseEntity<EnvioResponseDTO> entregarEnvio(@PathVariable Integer id) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(envioService.entregarEnvio(id));
    }
}
