package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;

@Repository
public interface SesionEntrenamientoRepository extends JpaRepository<SesionEntrenamiento, Long> {
    List<SesionEntrenamiento> findByEjercicioId(Long ejercicioId);
    List<SesionEntrenamiento> findByEstadoId(Long estadoId);
    List<SesionEntrenamiento> findByFechaRealizadoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<SesionEntrenamiento> findByFechaRealizadoAfter(LocalDateTime fecha);
    
    @Query("SELECT s FROM SesionEntrenamiento s WHERE s.ejercicio.nombre = :nombreEjercicio")
    List<SesionEntrenamiento> findByEjercicioNombre(@Param("nombreEjercicio") String nombreEjercicio);
    
    @Query("SELECT s FROM SesionEntrenamiento s WHERE s.estado.nombre = :nombreEstado")
    List<SesionEntrenamiento> findByEstadoNombre(@Param("nombreEstado") String nombreEstado);
    
    @Query("SELECT SUM(s.caloriasQuemadas) FROM SesionEntrenamiento s WHERE s.fechaRealizado BETWEEN :fechaInicio AND :fechaFin")
    Integer getTotalCaloriasQuemadasEnPeriodo(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
    
    @Query("SELECT AVG(s.duracion) FROM SesionEntrenamiento s WHERE s.ejercicio.id = :ejercicioId")
    Double getPromedioDuracionPorEjercicio(@Param("ejercicioId") Long ejercicioId);
}