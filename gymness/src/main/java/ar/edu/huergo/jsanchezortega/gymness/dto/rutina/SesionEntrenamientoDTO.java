package ar.edu.huergo.jsanchezortega.gymness.dto.rutina;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionEntrenamientoDTO {
    private Long id;
    
    @NotNull(message = "El nombre debe ser obligatoria")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    private LocalDateTime fechaRealizado = LocalDateTime.now();;
       
    @NotNull(message = "El estado es obligatorio")
    private Long estadoId;

    private Long rutinaId;

    private RutinaDTO rutina;
    
    private List<Long> ejercicioIds;

    private EstadoDTO estado;
}