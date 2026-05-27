package cl.triskeledu.usuarios.dto;

public record DireccionRequestDTO(
    String usuarioEmail,
    String calle,
    String ciudad,
    String region
) {}
