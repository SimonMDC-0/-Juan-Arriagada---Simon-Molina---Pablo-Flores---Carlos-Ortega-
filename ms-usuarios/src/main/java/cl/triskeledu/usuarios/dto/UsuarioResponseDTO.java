package cl.triskeledu.usuarios.dto;

public record UsuarioResponseDTO(
    String email,
    String nombre,
    String rolNombre,
    String estado
) {}
