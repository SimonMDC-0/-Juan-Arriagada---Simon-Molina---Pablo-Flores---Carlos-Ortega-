package cl.triskeledu.logistica.controller;

import cl.triskeledu.logistica.dto.EnvioRequestDTO;
import cl.triskeledu.logistica.dto.EnvioResponseDTO;
import cl.triskeledu.logistica.service.EnvioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<EnvioResponseDTO> programarEnvio(@RequestBody EnvioRequestDTO request) {
        return new ResponseEntity<>(envioService.programarEnvio(request), HttpStatus.CREATED);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<EnvioResponseDTO> obtenerEnvio(@PathVariable Integer pedidoId) {
        return ResponseEntity.ok(envioService.obtenerEnvioPorPedido(pedidoId));
    }

    @GetMapping
    public ResponseEntity<List<EnvioResponseDTO>> listarEnvios() {
        return ResponseEntity.ok(envioService.listarEnvios());
    }

    @PatchMapping("/{id}/despachar")
    public ResponseEntity<EnvioResponseDTO> despacharEnvio(@PathVariable Integer id) {
        return ResponseEntity.ok(envioService.despacharEnvio(id));
    }

    @PatchMapping("/{id}/entregar")
    public ResponseEntity<EnvioResponseDTO> entregarEnvio(@PathVariable Integer id) {
        return ResponseEntity.ok(envioService.entregarEnvio(id));
    }
}
