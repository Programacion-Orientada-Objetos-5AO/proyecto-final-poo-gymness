package ar.edu.huergo.jsanchezortega.gymness.entity.persona;

import java.util.List;

import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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


    @ManyToMany
    @JoinTable(
        name = "Profesionales_Planes",
        joinColumns = @JoinColumn(name = "profesional_id"),
        inverseJoinColumns = @JoinColumn(name = "plan_id")
    )
    private List<Plan> planes;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = true) 
    private Especialidad especialidad;
    
}
