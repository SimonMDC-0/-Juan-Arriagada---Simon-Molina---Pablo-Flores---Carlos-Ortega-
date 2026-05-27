package cl.triskeledu.usuarios.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.usuarios.dto.DireccionRequestDTO;
import cl.triskeledu.usuarios.dto.DireccionResponseDTO;
import cl.triskeledu.usuarios.dto.UsuarioRequestDTO;
import cl.triskeledu.usuarios.dto.UsuarioResponseDTO;
import cl.triskeledu.usuarios.exceptions.BadRequestException;
import cl.triskeledu.usuarios.exceptions.ResourceNotFoundException;
import cl.triskeledu.usuarios.model.Direccion;
import cl.triskeledu.usuarios.model.Rol;
import cl.triskeledu.usuarios.model.Usuario;
import cl.triskeledu.usuarios.repository.DireccionRepository;
import cl.triskeledu.usuarios.repository.RolRepository;
import cl.triskeledu.usuarios.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final DireccionRepository direccionRepository;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository, 
            RolRepository rolRepository, 
            DireccionRepository direccionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.direccionRepository = direccionRepository;
    }

    @Override
    @Transactional
    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        if (usuarioRepository.existsById(request.email())) {
            throw new BadRequestException("El usuario con email " + request.email() + " ya existe.");
        }

        Rol rol = rolRepository.findByNombreRol(request.rolNombre())
                .orElseThrow(() -> new ResourceNotFoundException("Rol " + request.rolNombre() + " no encontrado."));

        // Hash real con BCrypt (compatible con Spring Security)
        String passwordHash = passwordEncoder.encode(request.password());

        Usuario usuario = new Usuario(
                request.email(),
                request.nombre(),
                passwordHash,
                rol,
                "Activo"
        );

        Usuario saved = usuarioRepository.save(usuario);
        return mapToUsuarioResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerUsuarioPorEmail(String email) {
        log.info("Ejecutando lógica de servicio");
        Usuario usuario = usuarioRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con email " + email + " no encontrado."));
        return mapToUsuarioResponse(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarUsuarios() {
        log.info("Ejecutando lógica de servicio");
        return usuarioRepository.findAll().stream()
                .map(this::mapToUsuarioResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DireccionResponseDTO agregarDireccion(DireccionRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        if (!usuarioRepository.existsById(request.usuarioEmail())) {
            throw new ResourceNotFoundException("Usuario con email " + request.usuarioEmail() + " no encontrado.");
        }

        Direccion direccion = new Direccion(
                null,
                request.usuarioEmail(),
                request.calle(),
                request.ciudad(),
                request.region()
        );

        Direccion saved = direccionRepository.save(direccion);
        return mapToDireccionResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DireccionResponseDTO> listarDireccionesPorUsuario(String email) {
        log.info("Ejecutando lógica de servicio");
        if (!usuarioRepository.existsById(email)) {
            throw new ResourceNotFoundException("Usuario con email " + email + " no encontrado.");
        }
        return direccionRepository.findByUsuarioEmail(email).stream()
                .map(this::mapToDireccionResponse)
                .collect(Collectors.toList());
    }

    private UsuarioResponseDTO mapToUsuarioResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getRol().getNombreRol(),
                usuario.getEstado()
        );
    }

    private DireccionResponseDTO mapToDireccionResponse(Direccion direccion) {
        return new DireccionResponseDTO(
                direccion.getId(),
                direccion.getUsuarioEmail(),
                direccion.getCalle(),
                direccion.getCiudad(),
                direccion.getRegion()
        );
    }
}
