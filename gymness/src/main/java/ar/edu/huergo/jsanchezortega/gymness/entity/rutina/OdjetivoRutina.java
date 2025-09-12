package ar.edu.huergo.jsanchezortega.gymness.entity.rutina;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "odjetivo_rutina")
public class OdjetivoRutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre; // Ej: BAJAR_PESO, GANAR_MUSCULO, MATENIMIENTO

    public OdjetivoRutina(String nombre) {
        this.nombre = nombre;
    }
}
