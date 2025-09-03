package ar.edu.huergo.jsanchezortega.gymness.dto.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO extends PersonaDTO {
    
    @NotBlank(message = "Es obligatorio poner el nombre de la dirección")
    @Size(min = 2, max = 100, message = "La dirección debe tener entre 2 y 100 caracteres")
    private String direccion;
    
    @NotNull(message = "Es obligatorio colocar la altura de la calle")
    private Integer nroDireccion;
    
    @NotBlank(message = "Es obligatorio poner el nombre de la obra social")
    @Size(min = 2, max = 100, message = "La obra social debe tener entre 2 y 100 caracteres")
    private String obraSocial;
    
    @NotNull(message = "Es obligatorio poner la fecha de nacimiento")
    private LocalDate fechaNacimiento;
    
    private List<Long> planIds;
}
