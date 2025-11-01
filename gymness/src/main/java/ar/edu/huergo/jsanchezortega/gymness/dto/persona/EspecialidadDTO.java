package ar.edu.huergo.jsanchezortega.gymness.dto.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadDTO {
    private Long id;

    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
}
