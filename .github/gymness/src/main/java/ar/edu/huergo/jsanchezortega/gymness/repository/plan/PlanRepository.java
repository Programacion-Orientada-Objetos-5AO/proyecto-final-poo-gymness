package ar.edu.huergo.jsanchezortega.gymness.repository.plan;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    @Query("SELECT p FROM Plan p WHERE p.precio = (SELECT MIN(pl.precio) FROM Plan pl)")
    List<Plan> findPlanesMasEconomicos();
    
    @Query("SELECT p FROM Plan p WHERE p.precio = (SELECT MAX(pl.precio) FROM Plan pl)")
    List<Plan> findPlanesMasCaros();
    
    @Query("SELECT p FROM Plan p JOIN p.clientes c WHERE c.id = :clienteId")
    List<Plan> findByClienteId(@Param("clienteId") Long clienteId);
    
    @Query("SELECT p FROM Plan p WHERE SIZE(p.clientes) = 0")
    List<Plan> findPlanesSinClientes();
    
    @Query("SELECT p FROM Plan p WHERE SIZE(p.clientes) >= :minClientes")
    List<Plan> findPlanesConMinimosClientes(@Param("minClientes") int minClientes);
    
    @Query("SELECT p, SIZE(p.clientes) as cantidadClientes FROM Plan p ORDER BY SIZE(p.clientes) DESC")
    List<Object[]> findPlanesOrderByCantidadClientes();
    
    @Query("SELECT AVG(p.precio) FROM Plan p")
    Double getPromedioPrecioPlanes();
    
    @Query("SELECT COUNT(c) FROM Plan p JOIN p.clientes c WHERE p.id = :planId")
    Long countClientesByPlanId(@Param("planId") Long planId);
    
    @Query("SELECT p FROM Plan p WHERE p.precio BETWEEN :precioMin AND :precioMax AND SIZE(p.clientes) > 0")
    List<Plan> findPlanesActivosEnRangoPrecio(@Param("precioMin") Double precioMin, @Param("precioMax") Double precioMax);
}
