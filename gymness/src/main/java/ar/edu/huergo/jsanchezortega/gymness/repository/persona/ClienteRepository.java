package ar.edu.huergo.jsanchezortega.gymness.repository.persona;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Optional<Cliente> findByDocumento(String documento);
    boolean existsByDocumento(String documento); 
    List<Cliente> findByNombreContaining(String nombre);
    List<Cliente> findByApellidoContaining(String apellido);
    List<Cliente> findByObraSocial(String obraSocial);
    List<Cliente> findByDireccion(String direccion);
    List<Cliente> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Cliente> findByFechaNacimientoAfter(LocalDate fecha);
    List<Cliente> findByFechaNacimientoBefore(LocalDate fecha);
}
