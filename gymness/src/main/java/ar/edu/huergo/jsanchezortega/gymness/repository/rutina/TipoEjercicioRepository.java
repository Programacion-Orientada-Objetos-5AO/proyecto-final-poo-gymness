package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.TipoEjercicio;

@Repository
public interface TipoEjercicioRepository extends JpaRepository<TipoEjercicio, Long> {
    Optional<TipoEjercicio> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}