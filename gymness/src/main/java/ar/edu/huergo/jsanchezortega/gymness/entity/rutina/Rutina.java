package ar.edu.huergo.jsanchezortega.gymness.entity.rutina;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rutinas")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre; 
    
    @NotBlank(message = "La descripcion es obligatorio")
    @Size(min = 3, max = 255, message = "La descripcion debe tener entre 3 y 255 caracteres")
    private String descripcion;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "odjetivo_rutina_id", nullable = false)
    private OdjetivoRutina odjetivo; // Mantenimiento, Bajar de peso, Ganar musculo
    
    @OneToMany(mappedBy = "Rutina")
    private List<SesionEntrenamiento> sesiones;
}
