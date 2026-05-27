package cl.triskeledu.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DireccionRequestDTO(
    @NotBlank(message = "El campo usuarioEmail es obligatorio")
    String usuarioEmail,
    @NotBlank(message = "El campo calle es obligatorio")
    String calle,
    @NotBlank(message = "El campo ciudad es obligatorio")
    String ciudad,
    @NotBlank(message = "El campo region es obligatorio")
    String region
) {}
