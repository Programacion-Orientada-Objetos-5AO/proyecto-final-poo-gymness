package ar.edu.huergo.jsanchezortega.gymness.dto.rutina;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionEntrenamientoDTO {
    private Long id;
    
    @NotNull(message = "La fecha de realización es obligatoria")
    private LocalDateTime fechaRealizado;
       
    @NotNull(message = "El estado es obligatorio")
    private Long estadoId;
    
    private EjercicioDTO ejercicio;
    private EstadoDTO estado;
}