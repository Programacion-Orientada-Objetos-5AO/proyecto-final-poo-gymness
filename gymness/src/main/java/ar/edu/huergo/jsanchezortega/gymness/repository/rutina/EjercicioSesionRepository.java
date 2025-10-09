package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.EjercicioSesion;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EjercicioSesionRepository extends JpaRepository<EjercicioSesion, Long> {
    
    List<EjercicioSesion> findBySesion(SesionEntrenamiento sesion);
    List<EjercicioSesion> findBySesionId(@Param("sesionId") Long sesionId);
    List<EjercicioSesion> findByEjercicio(Ejercicio ejercicio);
    List<EjercicioSesion> findBySesionAndEjercicio(SesionEntrenamiento sesion, Ejercicio ejercicio);
    Optional<Integer> sumDuracionBySesionId(@Param("sesionId") Long sesionId);
    Optional<Double> avgPesoBySesionId(@Param("sesionId") Long sesionId);
    boolean existsBySesionAndEjercicio(SesionEntrenamiento sesion, Ejercicio ejercicio);
    void deleteBySesion(SesionEntrenamiento sesion);
    int countBySesion(SesionEntrenamiento sesion);
}