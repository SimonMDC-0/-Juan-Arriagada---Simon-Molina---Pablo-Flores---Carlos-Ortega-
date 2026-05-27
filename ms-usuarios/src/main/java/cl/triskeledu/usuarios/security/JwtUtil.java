package cl.triskeledu.usuarios.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret:mi-clave-secreta-super-segura-de-256-bits-para-jwt-tienda}")
    private String secret;

    @Value("${jwt.expiration:86400000}")  // 24 horas en ms por defecto
    private long expirationMs;

    // -------------------------------------------------------
    // Generacion de token
    // -------------------------------------------------------

    public String generarToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // Agregar el rol como claim adicional
        String roles = userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .reduce("", (a, b) -> a.isEmpty() ? b : a + "," + b);
        claims.put("roles", roles);
        return crearToken(claims, userDetails.getUsername());
    }

    private String crearToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSecretKey())
                .compact();
    }

    // -------------------------------------------------------
    // Validacion de token
    // -------------------------------------------------------

    public boolean validarToken(String token, UserDetails userDetails) {
        final String email = extraerEmail(token);
        return email.equals(userDetails.getUsername()) && !estaExpirado(token);
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // -------------------------------------------------------
    // Extraccion de datos
    // -------------------------------------------------------

    public String extraerEmail(String token) {
        return extraerClaim(token, Claims::getSubject);
    }

    public String extraerRoles(String token) {
        return extraerClaim(token, claims -> claims.get("roles", String.class));
    }

    private boolean estaExpirado(String token) {
        return extraerClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extraerClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        // JJWT 0.12.x requiere minimo 256 bits (32 bytes) para HS256
        if (keyBytes.length < 32) {
            byte[] padded = new byte[32];
            System.arraycopy(keyBytes, 0, padded, 0, keyBytes.length);
            keyBytes = padded;
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
