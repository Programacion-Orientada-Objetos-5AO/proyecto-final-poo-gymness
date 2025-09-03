package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Rutina;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    List<Rutina> findByOdjetivoId(Long objetivoId);
    List<Rutina> findByFechaCreacionBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Rutina> findByFechaCreacionAfter(LocalDate fecha);
    
    @Query("SELECT r FROM Rutina r WHERE r.odjetivo.nombre = :nombreObjetivo")
    List<Rutina> findByOdjetivoNombre(@Param("nombreObjetivo") String nombreObjetivo);
    
    @Query("SELECT r FROM Rutina r WHERE r.nombre LIKE %:nombre%")
    List<Rutina> findByNombreContaining(@Param("nombre") String nombre);
}
