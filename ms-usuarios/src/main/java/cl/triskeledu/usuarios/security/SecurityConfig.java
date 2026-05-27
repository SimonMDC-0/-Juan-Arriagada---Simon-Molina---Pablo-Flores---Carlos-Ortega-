package cl.triskeledu.usuarios.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuracion central de Spring Security.
 * - Stateless (JWT, sin sesiones HTTP)
 * - /api/auth/** es publico (login)
 * - /api/usuarios/** requiere autenticacion con rol ADMIN
 * - Endpoints de Actuator/Eureka permanecen accesibles
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserDetailsServiceImpl userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desactivar CSRF (no necesario para APIs REST stateless)
            .csrf(AbstractHttpConfigurer::disable)

            // Sin sesion HTTP — cada request debe autenticarse con JWT
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Reglas de acceso a endpoints
            .authorizeHttpRequests(auth -> auth
                // Login: publico, sin token
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

                // Registro de nuevo usuario: solo ADMIN puede crear usuarios
                .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMIN")

                // Listar todos los usuarios: solo ADMIN
                .requestMatchers(HttpMethod.GET, "/api/usuarios").hasRole("ADMIN")

                // Ver usuario por email, direcciones: requiere autenticacion (ADMIN o USER)
                .requestMatchers("/api/usuarios/**").authenticated()

                // Actuator y Eureka: acceso libre (solo monitoreo interno)
                .requestMatchers("/actuator/**").permitAll()

                // Cualquier otro endpoint requiere autenticacion
                .anyRequest().authenticated()
            )

            // Configurar el proveedor de autenticacion
            .authenticationProvider(authenticationProvider())

            // Agregar filtro JWT antes del filtro de usuario/contrasena
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
