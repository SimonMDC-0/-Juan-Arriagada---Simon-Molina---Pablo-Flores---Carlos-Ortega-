package cl.triskeledu.soporte.controller;

import cl.triskeledu.soporte.dto.TicketRequestDTO;
import cl.triskeledu.soporte.dto.TicketResponseDTO;
import cl.triskeledu.soporte.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> crearTicket(@RequestBody TicketRequestDTO request) {
        return new ResponseEntity<>(ticketService.crearTicket(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> obtenerTicket(@PathVariable Integer id) {
        return ResponseEntity.ok(ticketService.obtenerTicketPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TicketResponseDTO>> listarTicketsPorUsuario(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(ticketService.listarTicketsPorUsuario(usuarioId));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<TicketResponseDTO> actualizarEstado(@PathVariable Integer id, @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(ticketService.actualizarEstadoTicket(id, nuevoEstado));
    }
}
