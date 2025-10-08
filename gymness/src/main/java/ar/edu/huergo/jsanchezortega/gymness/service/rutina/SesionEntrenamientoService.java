package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearSesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.SesionEntrenamientoDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Estado;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.mapper.rutina.SesionEntrenamientoMapper;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.SesionEntrenamientoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SesionEntrenamientoService {

    @Autowired
    private SesionEntrenamientoRepository sesionEntrenamientoRepository;

    @Autowired
    private EjercicioService ejercicioService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private SesionEntrenamientoMapper sesionEntrenamientoMapper;

    public List<SesionEntrenamientoDTO> obtenerTodasLasSesiones() {
        List<SesionEntrenamiento> sesiones = sesionEntrenamientoRepository.findAll();
        return sesionEntrenamientoMapper.toDTOList(sesiones);
    }

    public SesionEntrenamientoDTO obtenerSesionPorId(Long id) throws EntityNotFoundException {
        SesionEntrenamiento sesion = sesionEntrenamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sesión de entrenamiento no encontrada"));
        return sesionEntrenamientoMapper.toDTO(sesion);
    }

    public SesionEntrenamientoDTO crearSesion(CrearSesionEntrenamiento dto) {
        Ejercicio ejercicio = ejercicioService.obtenerEjercicioId(dto.getEjercicioId());
        Estado estado = estadoService.obtenerEstadoPorId(dto.getEstadoId());

        SesionEntrenamiento sesion = sesionEntrenamientoMapper.toEntity(dto);
        sesion.setEjercicio(ejercicio);
        sesion.setEstado(estado);

        if (sesion.getFechaRealizado() == null) {
            sesion.setFechaRealizado(LocalDateTime.now());
        }

        SesionEntrenamiento sesionGuardada = sesionEntrenamientoRepository.save(sesion);
        return sesionEntrenamientoMapper.toDTO(sesionGuardada);
    }

    public SesionEntrenamientoDTO actualizarSesion(Long id, SesionEntrenamientoDTO dto) {
        SesionEntrenamiento sesionExistente = sesionEntrenamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sesión de entrenamiento no encontrada"));

        sesionExistente.setFechaRealizado(dto.getFechaRealizado());
    

        if (dto.getEstadoId() != null) {
            Estado estado = estadoService.obtenerEstadoPorId(dto.getEstadoId());
            sesionExistente.setEstado(estado);
        }

        SesionEntrenamiento sesionActualizada = sesionEntrenamientoRepository.save(sesionExistente);
        return sesionEntrenamientoMapper.toDTO(sesionActualizada);
    }

    public void eliminarSesion(Long id) throws EntityNotFoundException {
        SesionEntrenamiento sesion = sesionEntrenamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sesión de entrenamiento no encontrada"));
        sesionEntrenamientoRepository.delete(sesion);
    }
}