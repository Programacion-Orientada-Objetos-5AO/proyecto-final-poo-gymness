package ar.edu.huergo.jsanchezortega.gymness.dto.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EspecialidadDTO {
    @NotNull
    private Long id;

    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
}
