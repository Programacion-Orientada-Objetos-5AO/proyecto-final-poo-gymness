package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Rutina;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.EjercicioSesion;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Estado;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.SesionEntrenamientoRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.RutinaRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SesionEntrenamientoService {

    @Autowired
    private SesionEntrenamientoRepository sesionRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EjercicioSesionService ejercicioSesionService;

    public List<SesionEntrenamiento> obtenerTodasLasSesiones() {
        return sesionRepository.findAll();
    }

    public SesionEntrenamiento obtenerSesionPorId(Long id) throws EntityNotFoundException {
        return sesionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sesión de entrenamiento no encontrada"));
    }

    public SesionEntrenamiento crearSesion(SesionEntrenamiento sesion, Long rutinaId, Long estadoId, List<Long> ejercicioSesionIds) {
        if (rutinaId != null) {
            Rutina rutina = rutinaRepository.findById(rutinaId)
                    .orElseThrow(() -> new EntityNotFoundException("Rutina no encontrada"));
            sesion.setRutina(rutina);
        }

        if (estadoId != null) {
            Estado estado = estadoRepository.findById(estadoId)
                    .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));
            sesion.setEstado(estado);
        }

        List<EjercicioSesion> ejercicioSesions = ejercicioSesionService.resolvEjercicioSesions(ejercicioSesionIds);

        if (!ejercicioSesions.isEmpty()) {
            sesion.setEjercicio(ejercicioSesions);
        }
        return sesionRepository.save(sesion);
    }

    public SesionEntrenamiento actualizarSesion(Long id, SesionEntrenamiento sesion, Long rutinaId, Long estadoId, List<Long> ejercicioSesionIds)
            throws EntityNotFoundException {

        SesionEntrenamiento sesionExistente = obtenerSesionPorId(id);

        sesionExistente.setNombre(sesion.getNombre());
        sesionExistente.setFechaRealizado(sesion.getFechaRealizado());

        if (rutinaId != null) {
            Rutina rutina = rutinaRepository.findById(rutinaId)
                    .orElseThrow(() -> new EntityNotFoundException("Rutina no encontrada"));
            sesionExistente.setRutina(rutina);
        }

        if (estadoId != null) {
            Estado estado = estadoRepository.findById(estadoId)
                    .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));
            sesionExistente.setEstado(estado);
        }

        if (ejercicioSesionIds != null && !ejercicioSesionIds.isEmpty()) {
            List<EjercicioSesion> ejercicioSesions = ejercicioSesionService.resolvEjercicioSesions(ejercicioSesionIds);
            sesionExistente.setEjercicio(ejercicioSesions);
        }

        return sesionRepository.save(sesionExistente);
    }

    public void eliminarSesion(Long id) throws EntityNotFoundException {
        SesionEntrenamiento sesion = obtenerSesionPorId(id);
        sesionRepository.delete(sesion);
    }

    public List<SesionEntrenamiento> resolveSesionEntrenamientos(List<Long> sesionEntrenamientoIds) throws EntityNotFoundException{
        if (sesionEntrenamientoIds == null || sesionEntrenamientoIds.isEmpty()) {
                return new ArrayList<>(); 
        }

        List<SesionEntrenamiento> sesionEntrenamientos = sesionRepository.findAllById(sesionEntrenamientoIds);
        if (sesionEntrenamientos.size() != sesionEntrenamientoIds.stream().filter(Objects::nonNull).distinct()
                .count()) {
            throw new EntityNotFoundException("Una o mas sesione no existen");
        }
        return sesionEntrenamientos;
    }
}
