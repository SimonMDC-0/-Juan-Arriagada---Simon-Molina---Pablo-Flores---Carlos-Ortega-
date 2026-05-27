package cl.triskeledu.usuarios.controller;

import cl.triskeledu.usuarios.dto.DireccionRequestDTO;
import cl.triskeledu.usuarios.dto.DireccionResponseDTO;
import cl.triskeledu.usuarios.dto.UsuarioRequestDTO;
import cl.triskeledu.usuarios.dto.UsuarioResponseDTO;
import cl.triskeledu.usuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioRequestDTO request) {
        return new ResponseEntity<>(usuarioService.registrarUsuario(request), HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping("/{email}/direcciones")
    public ResponseEntity<DireccionResponseDTO> agregarDireccion(
            @PathVariable String email, 
            @RequestBody DireccionRequestDTO request) {
        // Asegurar que coincida con el email de la ruta
        DireccionRequestDTO dto = new DireccionRequestDTO(
                email,
                request.calle(),
                request.ciudad(),
                request.region()
        );
        return new ResponseEntity<>(usuarioService.agregarDireccion(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{email}/direcciones")
    public ResponseEntity<List<DireccionResponseDTO>> listarDirecciones(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.listarDireccionesPorUsuario(email));
    }
}
