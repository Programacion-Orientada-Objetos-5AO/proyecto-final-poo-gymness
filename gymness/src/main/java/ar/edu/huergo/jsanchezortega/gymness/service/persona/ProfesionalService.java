package ar.edu.huergo.jsanchezortega.gymness.service.persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Profesional;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Especialidad;
import ar.edu.huergo.jsanchezortega.gymness.repository.persona.ProfesionalRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.persona.EspecialidadRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfesionalService {
    
    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;
    
    public List<Profesional> obtenerTodosLosProfesionales() {
        return profesionalRepository.findAll();
    }

    public Profesional obtenerProfesionalPorId(Long id) throws EntityNotFoundException {
        return profesionalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));
    }

    public Profesional crearProfesional(Profesional profesional, Long especialidadId) {
        Especialidad especialidad = especialidadRepository.findById(especialidadId)
            .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
        profesional.setEspecialidad(especialidad);
        return profesionalRepository.save(profesional);
    }

    public Profesional actualizarProfesional(Long id, Profesional profesional, Long especialidadId) throws EntityNotFoundException {
        Profesional profesionalExistente = obtenerProfesionalPorId(id);
        profesionalExistente.setNombre(profesional.getNombre());
        profesionalExistente.setApellido(profesional.getApellido());
        profesionalExistente.setDocumento(profesional.getDocumento());
        profesionalExistente.setEmail(profesional.getEmail());
        profesionalExistente.setTelefono(profesional.getTelefono());
        profesionalExistente.setMatriculaProfesional(profesional.getMatriculaProfesional());
        profesionalExistente.setActivo(profesional.isActivo());
        
        if (especialidadId != null) {
            Especialidad especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
            profesionalExistente.setEspecialidad(especialidad);
        }

        return profesionalRepository.save(profesionalExistente);
    }
    
    public void eliminarProfesional(Long id) throws EntityNotFoundException {
        Profesional profesional = obtenerProfesionalPorId(id);
        profesionalRepository.delete(profesional);
    }

}
