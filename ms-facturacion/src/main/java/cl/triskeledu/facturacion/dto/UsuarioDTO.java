package cl.triskeledu.facturacion.dto;

public record UsuarioDTO(
        Integer id,
        String rut,
        String nombre,
        String email
) {}
