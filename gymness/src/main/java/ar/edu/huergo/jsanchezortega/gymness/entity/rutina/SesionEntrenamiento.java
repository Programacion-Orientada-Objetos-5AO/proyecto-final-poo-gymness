package ar.edu.huergo.jsanchezortega.gymness.entity.rutina;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    
    @ManyToOne
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;
    
    @Column(nullable = false)
    private LocalDateTime fechaRealizado;
    
    @Column(nullable = false)
    private Integer duracion; // en minutos
    
    @Column(nullable = false)
    private Integer caloriasQuemadas;
    
    @Column(nullable = false)
    private Integer series;
    
    @Column(nullable = false)
    private Integer repeticiones;
    
    @Column(nullable = false)
    private Double pesoUtilizado; // en kg
    
    @Column(nullable = false)
    private Integer rir; // Repeticiones en Reserva
    
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;
    
    // Constructor básico con ejercicio
    public SesionEntrenamiento(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
        this.fechaRealizado = LocalDateTime.now();
    }
}