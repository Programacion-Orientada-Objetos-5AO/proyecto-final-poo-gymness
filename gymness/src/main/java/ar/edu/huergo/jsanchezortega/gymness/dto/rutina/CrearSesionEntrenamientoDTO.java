package ar.edu.huergo.jsanchezortega.gymness.dto.rutina;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CrearSesionEntrenamientoDTO {
    @NotNull(message = "La fecha de realización es obligatoria")
    private String nombre;

    @NotNull(message = "La fecha de realización es obligatoria")
    private LocalDateTime fechaRealizado;
       
    @NotNull(message = "El estado es obligatorio")
    private Long estadoId;

    private Long rutinaId;

    private RutinaDTO rutina;
    
    private List<Long> ejercicioIds;

    private EstadoDTO estado;
}
