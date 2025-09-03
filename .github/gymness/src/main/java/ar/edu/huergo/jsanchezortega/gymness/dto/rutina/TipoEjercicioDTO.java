package ar.edu.huergo.jsanchezortega.gymness.dto.rutina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TipoEjercicioDTO {
    @NotNull
    private Long id;

    @NotBlank(message = "El nombre del tipo de ejercicio es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
}
