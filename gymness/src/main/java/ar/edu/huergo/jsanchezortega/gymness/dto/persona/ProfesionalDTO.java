package ar.edu.huergo.jsanchezortega.gymness.dto.persona;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProfesionalDTO extends PersonaDTO {
    
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
    
    private EspecialidadDTO especialidad;
}