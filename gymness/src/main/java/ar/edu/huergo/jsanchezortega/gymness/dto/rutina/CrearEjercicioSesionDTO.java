package ar.edu.huergo.jsanchezortega.gymness.dto.rutina;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearEjercicioSesionDTO {
    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser al menos de 1 minuto")
    private Integer duracion; // en minutos

    @NotNull(message = "Las calorías quemadas son obligatorias")
    @Min(value = 0, message = "Las calorías no pueden ser negativas")
    private Integer caloriasQuemadas;

    @NotNull(message = "Las series son obligatorias")
    @Min(value = 1, message = "Debe haber al menos 1 serie")
    private Integer series;

    @NotNull(message = "Las repeticiones son obligatorias")
    @Min(value = 1, message = "Debe haber al menos 1 repetición")
    private Integer repeticiones;

    @NotNull(message = "El peso utilizado es obligatorio")
    @Min(value = 0, message = "El peso no puede ser negativo")
    private Double pesoUtilizado; // en kg

    @NotNull(message = "El RIR (repeticiones en reserva) es obligatorio")
    @Min(value = 0, message = "El RIR no puede ser negativo")
    private Integer rir;

    @NotNull(message = "Debe indicar la sesión de entrenamiento asociada")
    private Long sesionId;

    @NotNull(message = "Debe indicar el ejercicio asociado")
    private Long ejercicioId;
    
}
