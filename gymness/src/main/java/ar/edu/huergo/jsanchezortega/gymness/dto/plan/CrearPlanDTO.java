package ar.edu.huergo.jsanchezortega.gymness.dto.plan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearPlanDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 3, max = 255, message = "La descripción debe tener entre 3 y 255 caracteres")
    private String descripcion;
    
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;
}