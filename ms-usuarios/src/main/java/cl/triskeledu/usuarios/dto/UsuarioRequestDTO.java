package cl.triskeledu.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
    @NotBlank(message = "El campo email es obligatorio")
    String email,
    @NotBlank(message = "El campo nombre es obligatorio")
    String nombre,
    @NotBlank(message = "El campo password es obligatorio")
    String password,
    @NotBlank(message = "El campo rolNombre es obligatorio")
    String rolNombre
) {}
