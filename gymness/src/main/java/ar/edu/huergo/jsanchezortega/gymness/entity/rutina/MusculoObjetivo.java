package ar.edu.huergo.jsanchezortega.gymness.entity.rutina;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "musculo_odjetivo")
public class MusculoObjetivo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre; // Ej: PECHO, ESPALDA, TRICEP.ETC

    @ManyToMany(mappedBy = "musculosObjetivo")
    private List<Ejercicio> ejercicios;

    public MusculoObjetivo(String nombre) {
        this.nombre = nombre;
    }
}