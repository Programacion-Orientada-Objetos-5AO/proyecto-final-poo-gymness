package ar.edu.huergo.jsanchezortega.gymness.entity.rutina;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class EjercicioSesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinColumn(name = "sesion_id", nullable = false)
    private SesionEntrenamiento sesion;

    @ManyToOne
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;
    
}
