package ar.edu.huergo.jsanchezortega.gymness.entity.persona;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate; // import the LocalDate class
import java.util.List;

import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
@EqualsAndHashCode(callSuper = true)


public class Cliente extends Persona{

    @NotBlank(message = "Es obligatorio poner el nombre de la direccion")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caractertes")
    private String direccion;

    @NotBlank(message = "Es obligatorio colocar la altura de la calle")
    private int nroDireccion;

    @NotBlank(message = "Es obligatorio poner el nombre de la obra social")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caractertes")
    private String obraSocial;

    @NotNull(message = "Es obligatorio poner la fecha de nacimiento")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @OneToMany
    @JoinTable(name = "Clientes_Planes",
       joinColumns = @JoinColumn(name = "cliente_id"),
       inverseJoinColumns = @JoinColumn(name = "plan_id"))
    private List<Plan> planes;
    
}
