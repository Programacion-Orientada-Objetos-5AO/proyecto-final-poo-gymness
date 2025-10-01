package ar.edu.huergo.jsanchezortega.gymness.entity.persona;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("PROFESIONAL")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Profesional extends Persona{

    
    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 100, message = "El email no puede superar los 100 caracteres")
    private String email;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;

    @NotBlank(message = "La matricula del profesional es obligatoria")
    @Size(max = 100, message = "La matricula no puede superar los 100 caracteres")
    private String matriculaProfesional; // si aplica

    @NotNull(message = "Se debe saber el estado del profresional")
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = true) 
    private Especialidad especialidad;
    
}
