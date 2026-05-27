package cl.triskeledu.soporte.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.soporte.dto.TicketRequestDTO;
import cl.triskeledu.soporte.dto.TicketResponseDTO;
import cl.triskeledu.soporte.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/soporte/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> crearTicket(@Valid @RequestBody TicketRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        return new ResponseEntity<>(ticketService.crearTicket(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> obtenerTicket(@PathVariable Integer id) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(ticketService.obtenerTicketPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TicketResponseDTO>> listarTicketsPorUsuario(@PathVariable Integer usuarioId) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(ticketService.listarTicketsPorUsuario(usuarioId));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<TicketResponseDTO> actualizarEstado(@PathVariable Integer id, @RequestParam String nuevoEstado) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(ticketService.actualizarEstadoTicket(id, nuevoEstado));
    }
}
