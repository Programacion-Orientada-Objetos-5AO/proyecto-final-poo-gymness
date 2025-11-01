package ar.edu.huergo.jsanchezortega.gymness.dto.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonaDTO {
    private Long id;
    
    @NotBlank(message = "El nombre debe ser obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "El apellido debe ser obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;
    
    @NotBlank(message = "El documento de identidad es obligatorio")
    @Size(max = 8, message = "El documento debe tener máximo 8 dígitos")
    private Integer documento;
}