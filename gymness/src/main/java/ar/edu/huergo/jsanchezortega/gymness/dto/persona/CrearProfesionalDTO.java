package ar.edu.huergo.jsanchezortega.gymness.dto.persona;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    
    @NotNull(message = "El documento de identidad es obligatorio")
    @Min(value = 10000000, message = "El documento debe tener al menos 8 dígitos")
    @Max(value = 99999999, message = "El documento no puede tener más de 8 dígitos")
    private Integer documento;
    
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
