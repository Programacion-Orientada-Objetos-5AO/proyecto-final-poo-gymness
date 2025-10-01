package ar.edu.huergo.jsanchezortega.gymness.dto.persona;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarClienteDTO {
    
    @NotBlank(message = "El nombre debe ser obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "El apellido debe ser obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;
    
    @NotBlank(message = "El documento de identidad es obligatorio")
    @Size(min = 8, max = 8, message = "El documento debe tener exactamente 8 dígitos")
    private String documento; 
    
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

    private List<Long> planIds; // plural
}

