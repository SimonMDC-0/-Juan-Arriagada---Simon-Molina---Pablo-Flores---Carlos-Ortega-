package cl.triskeledu.tiendas.dto;

public record TiendaResponseDTO(
        Integer id,
        String nombre,
        String direccion,
        String telefono,
        String horario
) {
}
