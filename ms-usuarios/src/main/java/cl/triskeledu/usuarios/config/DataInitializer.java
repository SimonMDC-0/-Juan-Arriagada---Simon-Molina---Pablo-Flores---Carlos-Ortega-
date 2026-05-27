package cl.triskeledu.usuarios.config;

import cl.triskeledu.usuarios.model.Rol;
import cl.triskeledu.usuarios.model.Usuario;
import cl.triskeledu.usuarios.repository.RolRepository;
import cl.triskeledu.usuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DataInitializer(RolRepository rolRepository, UsuarioRepository usuarioRepository) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean necesitaInit = rolRepository.count() == 0;

        // Si ya existen datos pero la contraseña del admin no esta en BCrypt, re-inicializar
        if (!necesitaInit && usuarioRepository.existsById("admin@test.com")) {
            String passActual = usuarioRepository.findById("admin@test.com")
                    .map(u -> u.getPasswordHash())
                    .orElse("");
            // Si la contraseña no empieza con $2a$ o $2b$ no es BCrypt → re-inicializar
            if (!passActual.startsWith("$2a$") && !passActual.startsWith("$2b$")) {
                usuarioRepository.deleteAll();
                rolRepository.deleteAll();
                necesitaInit = true;
            }
        }

        if (necesitaInit) {
            Rol admin = rolRepository.save(new Rol(null, "ADMIN", "Administrador"));
            Rol user  = rolRepository.save(new Rol(null, "USER", "Usuario Normal"));

            // Guardar contraseñas con BCrypt para que Spring Security pueda verificarlas
            usuarioRepository.save(new Usuario(
                    "admin@test.com",
                    "Admin Inicial",
                    passwordEncoder.encode("123456"),
                    admin,
                    "Activo"
            ));
            usuarioRepository.save(new Usuario(
                    "cliente@test.com",
                    "Cliente Test",
                    passwordEncoder.encode("123456"),
                    user,
                    "Activo"
            ));
        } else {
            // Asegurar que las contraseñas preexistentes en la BD (insertadas por el script SQL)
            // estén hasheadas correctamente con BCrypt
            usuarioRepository.findById("admin@ecomarket.cl").ifPresent(u -> {
                String pass = u.getPasswordHash();
                if (!pass.startsWith("$2a$") || pass.contains("hashAdmin")) {
                    u.setPasswordHash(passwordEncoder.encode("123456"));
                    usuarioRepository.save(u);
                }
            });
            usuarioRepository.findById("gerente@ecomarket.cl").ifPresent(u -> {
                String pass = u.getPasswordHash();
                if (!pass.startsWith("$2a$") || pass.contains("hashGerente")) {
                    u.setPasswordHash(passwordEncoder.encode("123456"));
                    usuarioRepository.save(u);
                }
            });
            usuarioRepository.findById("juan@cliente.cl").ifPresent(u -> {
                String pass = u.getPasswordHash();
                if (!pass.startsWith("$2a$") || pass.contains("hashCliente")) {
                    u.setPasswordHash(passwordEncoder.encode("123456"));
                    usuarioRepository.save(u);
                }
            });
        }
    }
}


