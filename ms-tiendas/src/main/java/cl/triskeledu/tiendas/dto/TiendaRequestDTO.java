package cl.triskeledu.tiendas.dto;

public record TiendaRequestDTO(
        String nombre,
        String direccion,
        String telefono,
        String horario
) {
}
