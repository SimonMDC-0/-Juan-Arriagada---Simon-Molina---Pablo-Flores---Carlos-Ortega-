package cl.triskeledu.usuarios.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.usuarios.dto.LoginRequestDTO;
import cl.triskeledu.usuarios.dto.LoginResponseDTO;
import cl.triskeledu.usuarios.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de autenticacion.
 * POST /api/auth/login  →  retorna JWT token si las credenciales son correctas.
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generarToken(userDetails);
            String rol = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(a -> a.getAuthority().replace("ROLE_", ""))
                    .orElse("UNKNOWN");

            return ResponseEntity.ok(new LoginResponseDTO(token, userDetails.getUsername(), rol));

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(401)
                    .body(java.util.Map.of("error", "Credenciales incorrectas"));
        }
    }
}
