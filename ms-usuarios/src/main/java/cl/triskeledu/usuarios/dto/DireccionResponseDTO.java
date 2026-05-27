package cl.triskeledu.usuarios.dto;

public record DireccionResponseDTO(
    Integer id,
    String usuarioEmail,
    String calle,
    String ciudad,
    String region
) {}
