package ar.edu.huergo.jsanchezortega.gymness.entity.rutina;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ejercicios")
public class Ejercicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 100)
    private String nombre; // Ej: "Press de banca", "Sentadillas"
    
    @Column(nullable = false, length = 500)
    private String descripcion;
    
    
    
    @ManyToOne
    @JoinColumn(name = "tipo_ejercicio_id", nullable = false)
    private TipoEjercicio tipoEjercicio;
    
    @Column(length = 1000)
    private String instrucciones;
    
    @Column(length = 255)
    private String videoUrl;
    
    @Column(length = 255)
    private String imagenUrl;
        
        @Column(nullable = false)
    private Boolean activo = true;

    @ManyToMany
    @JoinTable(
        name = "ejercicio_musculo",
        joinColumns = @JoinColumn(name = "ejercicio_id"),
        inverseJoinColumns = @JoinColumn(name = "musculo_id")
    )
    private List<MusculoObjetivo> musculosObjetivo = new ArrayList<>();
    
}
