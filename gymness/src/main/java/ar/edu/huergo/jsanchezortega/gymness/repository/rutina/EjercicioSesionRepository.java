package ar.edu.huergo.jsanchezortega.gymness.repository.rutina;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.EjercicioSesion;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjercicioSesionRepository extends JpaRepository<EjercicioSesion, Long> {
    
    List<EjercicioSesion> findBySesion(SesionEntrenamiento sesion);
    List<EjercicioSesion> findBySesionId(@Param("sesionId") Long sesionId);
    List<EjercicioSesion> findByEjercicio(Ejercicio ejercicio);
    List<EjercicioSesion> findBySesionAndEjercicio(SesionEntrenamiento sesion, Ejercicio ejercicio);
    boolean existsBySesionAndEjercicio(SesionEntrenamiento sesion, Ejercicio ejercicio);
    void deleteBySesion(SesionEntrenamiento sesion);
    int countBySesion(SesionEntrenamiento sesion);
}