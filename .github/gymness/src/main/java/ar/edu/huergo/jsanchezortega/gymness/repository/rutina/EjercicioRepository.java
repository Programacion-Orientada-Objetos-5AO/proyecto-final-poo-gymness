package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    Optional<Ejercicio> findByNombre(String nombre);
    List<Ejercicio> findByActivo(Boolean activo);
    List<Ejercicio> findByMusculoObjetivoId(Long musculoObjetivoId);
    List<Ejercicio> findByTipoEjercicioId(Long tipoEjercicioId);
    boolean existsByNombre(String nombre);
    
    @Query("SELECT e FROM Ejercicio e WHERE e.musculoObjetivo.nombre = :musculoNombre AND e.activo = true")
    List<Ejercicio> findByMusculoObjetivoNombreAndActivo(@Param("musculoNombre") String musculoNombre);
    
    @Query("SELECT e FROM Ejercicio e WHERE e.tipoEjercicio.nombre = :tipoNombre AND e.activo = true")
    List<Ejercicio> findByTipoEjercicioNombreAndActivo(@Param("tipoNombre") String tipoNombre);
}
