package ar.edu.huergo.jsanchezortega.gymness.entity.persona;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profesional")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profesional extends Persona{

    
    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 100, message = "El email no puede superar los 100 caracteres")
    private String email;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;


    @NotBlank(message = "La matricula del profesional es obligatoria")
    @Size(max = 100, message = "La matricula no puede superar los 100 caracteres")
    private String matriculaProfesional; // si aplica

    @NotBlank(message = "Se debe saber el estado del profresional")
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false) // FK en la tabla profesional
    private Especialidad especialidad;
    
}
