package ar.edu.huergo.jsanchezortega.gymness.service.persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Especialidad;
import ar.edu.huergo.jsanchezortega.gymness.repository.persona.EspecialidadRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EspecialidadService {
    @Autowired

    private EspecialidadRepository especialidadRepository;
    
    public List<Especialidad> obtenerTodasLasEspecialidades() {
        return especialidadRepository.findAll();
    }

    public Especialidad obtenerEspecialidadPorId(Long id) throws EntityNotFoundException {
        return especialidadRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
    }

    public Especialidad crearEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public Especialidad actualizarEspecialidad(Long id, Especialidad especialidad) throws EntityNotFoundException {
        Especialidad especialidadExistente = obtenerEspecialidadPorId(id);
        especialidadExistente.setNombre(especialidad.getNombre());
        return especialidadRepository.save(especialidadExistente);
    }
    
    public void eliminarEspecialidad(Long id) throws EntityNotFoundException {
        Especialidad especialidad = obtenerEspecialidadPorId(id);
        especialidadRepository.delete(especialidad);
    }
}
