package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.OdjetivoRutina;

@Repository
public interface OdjetivoRutinaRepository extends JpaRepository<OdjetivoRutina, Long> {
    Optional<OdjetivoRutina> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}