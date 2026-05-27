package cl.triskeledu.usuarios.security;

import cl.triskeledu.usuarios.model.Usuario;
import cl.triskeledu.usuarios.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Carga los datos del usuario desde la base de datos para Spring Security.
 * El "username" en Spring Security corresponde al email del usuario.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado con email: " + email));

        // Mapear los roles de la base de datos a los roles estandar de Spring Security
        String dbRol = usuario.getRol().getNombreRol().toUpperCase();
        String rolConPrefijo;
        if (dbRol.contains("ADMIN") || dbRol.equals("ADMINISTRADOR")) {
            rolConPrefijo = "ROLE_ADMIN";
        } else if (dbRol.contains("CLIENTE") || dbRol.contains("USER")) {
            rolConPrefijo = "ROLE_USER";
        } else {
            rolConPrefijo = "ROLE_" + usuario.getRol().getNombreRol();
        }

        return new User(
                usuario.getEmail(),
                usuario.getPasswordHash(),
                List.of(new SimpleGrantedAuthority(rolConPrefijo))
        );
    }
}
