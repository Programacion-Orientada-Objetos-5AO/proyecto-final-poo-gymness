package ar.edu.huergo.jsanchezortega.gymness.dto.rutina;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearRutinaDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 3, max = 255, message = "La descripción debe tener entre 3 y 255 caracteres")
    private String descripcion;
    
    @NotNull(message = "La fecha de creacion de la rutina no puede estar vacio")
    private LocalDate fechaCreacion;
    
    @NotNull(message = "El objetivo de la rutina es obligatorio")
    private Long objetivoId;
    
}
