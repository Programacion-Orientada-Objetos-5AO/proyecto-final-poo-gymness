package ar.edu.huergo.jsanchezortega.gymness.repository.persona;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Especialidad;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    Optional<Especialidad> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}