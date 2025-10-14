package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.EjercicioSesion;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.EjercicioRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.EjercicioSesionRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.SesionEntrenamientoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EjercicioSesionService {

    @Autowired
    private EjercicioSesionRepository ejercicioSesionRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private SesionEntrenamientoRepository sesionEntrenamientoRepository;

    public List<EjercicioSesion> obtenerTodosLosEjerciciosSesion(){
        return ejercicioSesionRepository.findAll();
    }

    public EjercicioSesion obtenerEjercicioSesionId(Long id) throws EntityNotFoundException{ 
        return ejercicioSesionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ejercicio de sesión no encontrado"));
    }

    public EjercicioSesion crearEjercicioSesion(EjercicioSesion ejercicioSesion, Long sesionId, Long ejercicioId){
        if (ejercicioId != null) {
            Ejercicio ejercicio = ejercicioRepository.findById(ejercicioId)
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado"));
                ejercicioSesion.setEjercicio(ejercicio);
        }

        if (sesionId != null) {
            SesionEntrenamiento sesion = sesionEntrenamientoRepository.findById(sesionId)
                .orElseThrow(() -> new EntityNotFoundException("Sesion no encontrado"));
                ejercicioSesion.setSesion(sesion);
        }


        return ejercicioSesionRepository.save(ejercicioSesion);
    }

    public EjercicioSesion actualizarEjercicioSesion(Long id, EjercicioSesion ejercicioSesion, Long sesionId, Long ejercicioId) throws EntityNotFoundException{
        EjercicioSesion ejercicioSesionExistente = obtenerEjercicioSesionId(id);
        ejercicioSesionExistente.setCaloriasQuemadas(ejercicioSesion.getCaloriasQuemadas());
        ejercicioSesionExistente.setRir(ejercicioSesion.getRir());
        ejercicioSesionExistente.setSeries(ejercicioSesion.getSeries());
        ejercicioSesionExistente.setRepeticiones(ejercicioSesion.getRepeticiones());
        ejercicioSesionExistente.setPesoUtilizado(ejercicioSesion.getPesoUtilizado());
        ejercicioSesionExistente.setDuracion(ejercicioSesion.getDuracion());

        if (ejercicioId != null) {
            Ejercicio ejercicio = ejercicioRepository.findById(ejercicioId)
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado"));
                ejercicioSesionExistente.setEjercicio(ejercicio);
        }

        if (sesionId != null) {
            SesionEntrenamiento sesion = sesionEntrenamientoRepository.findById(sesionId)
                .orElseThrow(() -> new EntityNotFoundException("Sesion no encontrado"));
                ejercicioSesionExistente.setSesion(sesion);
        }
        
        return ejercicioSesionRepository.save(ejercicioSesionExistente);
    }

    public void eliminarEjercicioSesion(Long id) {
        if (!ejercicioSesionRepository.existsById(id)) {
            throw new EntityNotFoundException("El ejercicio de sesión con id " + id + " no existe");
        }
        ejercicioSesionRepository.deleteById(id);
    }

    public List<EjercicioSesion> resolvEjercicioSesions(List<Long> ejercicioSesionIds){
        if (ejercicioSesionIds == null || ejercicioSesionIds.isEmpty()) {
            return List.of();
        }

        List<EjercicioSesion> ejercicioSesions = ejercicioSesionRepository.findAllById(ejercicioSesionIds);

        if (ejercicioSesions.size() != ejercicioSesionIds.stream().filter(Objects::nonNull).distinct().count()) {
            throw new EntityNotFoundException("Uno o más Ejercicos en la sesion no existen");
        }

        return ejercicioSesions;
    }

}
