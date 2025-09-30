package ar.edu.huergo.jsanchezortega.gymness.dto.persona;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearProfesionalDTO {
    @NotBlank(message = "El nombre debe ser obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "El apellido debe ser obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;
    
    @NotBlank(message = "El documento de identidad es obligatorio")
    @Size(min = 8, max = 8, message = "El documento debe tener exactamente 8 dígitos")
    private String documento; 
    
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 100, message = "El email no puede superar los 100 caracteres")
    private String email;
    
    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;
    
    @NotBlank(message = "La matrícula del profesional es obligatoria")
    @Size(max = 100, message = "La matrícula no puede superar los 100 caracteres")
    private String matriculaProfesional;
    
    @NotNull(message = "Se debe saber el estado del profesional")
    private Boolean activo;
    
    @NotNull(message = "La especialidad es obligatoria")
    private Long especialidadId;
}
