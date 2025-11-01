package ar.edu.huergo.jsanchezortega.gymness.repository.plan;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Optional<Plan> findByNombre(String nombre);
    List<Plan> findByPrecioBetween(Double precioMin, Double precioMax);
    List<Plan> findByPrecioLessThan(Double precio);
    List<Plan> findByPrecioGreaterThan(Double precio);
    List<Plan> findByDescripcionContaining(String descripcion);
    List<Plan> findByNombreContaining(String nombre);
    boolean existsByNombre(String nombre);
    
}
