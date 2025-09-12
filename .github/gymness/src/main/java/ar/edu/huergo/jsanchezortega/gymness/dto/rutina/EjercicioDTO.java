package ar.edu.huergo.jsanchezortega.gymness.dto.rutina;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EjercicioDTO {
    private Long id;
    
    @NotBlank(message = "El nombre del ejercicio es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 3, max = 500, message = "La descripción debe tener entre 3 y 500 caracteres")
    private String descripcion;
    
    @Size(max = 1000, message = "Las instrucciones no pueden superar los 1000 caracteres")
    private String instrucciones;
    
    @Size(max = 255, message = "La URL del video no puede superar los 255 caracteres")
    private String videoUrl;
    
    @Size(max = 255, message = "La URL de la imagen no puede superar los 255 caracteres")
    private String imagenUrl;
    
    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;
    
    @NotEmpty(message = "Se debe seleccionar al menos un  músculo objetivo")
    private List<Long> musculoObjetivoIds;
    
    @NotEmpty(message = "Se debe seleccionar al menos un  tipo de ejercicio")
    private Long tipoEjercicioIds;
    
    private List<MusculoOdjetivoDTO> musculoObjetivo;
    private TipoEjercicioDTO tipoEjercicio;
}