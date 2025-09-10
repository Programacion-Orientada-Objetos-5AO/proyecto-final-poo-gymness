package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.MusculoObjetivo;

@Repository
public interface MusculoObjetivoRepository extends JpaRepository<MusculoObjetivo, Long> {
    List<MusculoObjetivo> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}