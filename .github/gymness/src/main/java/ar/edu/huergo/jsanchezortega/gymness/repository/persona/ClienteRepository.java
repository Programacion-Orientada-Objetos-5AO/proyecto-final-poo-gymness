package ar.edu.huergo.jsanchezortega.gymness.repository.persona;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
     Optional<Cliente> findByDocumento(Integer documento);
    List<Cliente> findByNombreContaining(String nombre);
    List<Cliente> findByApellidoContaining(String apellido);
    List<Cliente> findByObraSocial(String obraSocial);
    List<Cliente> findByDireccion(String direccion);
    List<Cliente> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Cliente> findByFechaNacimientoAfter(LocalDate fecha);
    List<Cliente> findByFechaNacimientoBefore(LocalDate fecha);
    boolean existsByDocumento(Integer documento);
    
    @Query("SELECT c FROM Cliente c WHERE c.nombre LIKE %:nombre% AND c.apellido LIKE %:apellido%")
    List<Cliente> findByNombreAndApellidoContaining(@Param("nombre") String nombre, @Param("apellido") String apellido);
    
    @Query("SELECT c FROM Cliente c JOIN c.planes p WHERE p.id = :planId")
    List<Cliente> findByPlanId(@Param("planId") Long planId);
    
    @Query("SELECT c FROM Cliente c JOIN c.planes p WHERE p.nombre = :nombrePlan")
    List<Cliente> findByPlanNombre(@Param("nombrePlan") String nombrePlan);
    
    @Query("SELECT COUNT(c) FROM Cliente c WHERE YEAR(c.fechaNacimiento) BETWEEN :anioInicio AND :anioFin")
    Long countByRangoEdad(@Param("anioInicio") int anioInicio, @Param("anioFin") int anioFin);
    
    @Query("SELECT c FROM Cliente c WHERE SIZE(c.planes) = 0")
    List<Cliente> findClientesSinPlan();
    
    @Query("SELECT c FROM Cliente c WHERE SIZE(c.planes) > 1")
    List<Cliente> findClientesConMultiplesPlanes();
}
