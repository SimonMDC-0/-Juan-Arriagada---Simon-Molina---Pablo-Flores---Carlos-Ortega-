package cl.triskeledu.usuarios.dto;

public record UsuarioRequestDTO(
    String email,
    String nombre,
    String password,
    String rolNombre
) {}
