package ar.edu.huergo.jsanchezortega.gymness.dto.rutina;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CrearSesionEntrenamiento {
    @NotNull(message = "El ejercicio es obligatorio")
    private Long ejercicioId;
    
    private LocalDateTime fechaRealizado;
    
    @NotNull(message = "La duración es obligatoria")
    @Positive(message = "La duración debe ser mayor a 0")
    private Integer duracion;
    
    @NotNull(message = "Las calorías quemadas son obligatorias")
    @Positive(message = "Las calorías quemadas deben ser mayor a 0")
    private Integer caloriasQuemadas;
    
    @NotNull(message = "Las series son obligatorias")
    @Positive(message = "Las series deben ser mayor a 0")
    private Integer series;
    
    @NotNull(message = "Las repeticiones son obligatorias")
    @Positive(message = "Las repeticiones deben ser mayor a 0")
    private Integer repeticiones;
    
    @NotNull(message = "El peso utilizado es obligatorio")
    @Positive(message = "El peso utilizado debe ser mayor a 0")
    private Double pesoUtilizado;
    
    @NotNull(message = "El RIR es obligatorio")
    private Integer rir;
    
    @NotNull(message = "El estado es obligatorio")
    private Long estadoId;
}
