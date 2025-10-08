package ar.edu.huergo.jsanchezortega.gymness.entity.rutina;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
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
@Table(name = "sesiones_entrenamiento")
public class SesionEntrenamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "rutina_id", nullable = false)
    private Rutina rutina;
    
    @OneToMany (mappedBy = "sesion")
    private List<EjercicioSesion> ejercicio;
    
    @Column(nullable = false)
    private LocalDateTime fechaRealizado;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;
    
}