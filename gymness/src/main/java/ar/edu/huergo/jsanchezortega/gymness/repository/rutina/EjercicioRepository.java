package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    Optional<Ejercicio> findByNombre(String nombre);
    List<Ejercicio> findByActivo(Boolean activo);
    List<Ejercicio> findByMusculosObjetivo_Id(Long id);
    List<Ejercicio> findByTipoEjercicioId(Long tipoEjercicioId);
    boolean existsByNombre(String nombre);
    
}
