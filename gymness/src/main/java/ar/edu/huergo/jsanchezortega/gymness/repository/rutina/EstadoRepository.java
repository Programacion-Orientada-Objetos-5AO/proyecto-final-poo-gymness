package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}