package cl.triskeledu.usuarios.dto;

public record LoginResponseDTO(
        String token,
        String email,
        String rol
) {}
