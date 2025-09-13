package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;

@Repository
public interface SesionEntrenamientoRepository extends JpaRepository<SesionEntrenamiento, Long> {
    List<SesionEntrenamiento> findByEjercicioId(Long ejercicioId);
    List<SesionEntrenamiento> findByEstadoId(Long estadoId);
    List<SesionEntrenamiento> findByFechaRealizadoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<SesionEntrenamiento> findByFechaRealizadoAfter(LocalDateTime fecha);
    
}