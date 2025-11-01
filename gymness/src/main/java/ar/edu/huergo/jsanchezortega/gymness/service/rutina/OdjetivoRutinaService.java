package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.OdjetivoRutina;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.OdjetivoRutinaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OdjetivoRutinaService {
    @Autowired
    private OdjetivoRutinaRepository odjetivoRutinaRepository;

    public List<OdjetivoRutina> obtenerTodoLosOdjetivoRutinas(){
        return odjetivoRutinaRepository.findAll();
    }

    public OdjetivoRutina obteneOdjetivoRutinaPorId(Long id) throws EntityNotFoundException{
        return odjetivoRutinaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Odjetivo de rutina no encontrado"));
    }

    public OdjetivoRutina crearOdjetivoRutina(OdjetivoRutina odjetivo){
        return odjetivoRutinaRepository.save(odjetivo);
    }

    public OdjetivoRutina actualizarOdjetivoRutina(Long id, OdjetivoRutina odjetivoRutina){
        OdjetivoRutina odjetivoRutinaExistente = obteneOdjetivoRutinaPorId(id);
        odjetivoRutinaExistente.setNombre(odjetivoRutina.getNombre());
        return odjetivoRutinaRepository.save(odjetivoRutinaExistente);
    }

    public void eliminarOdjetivoRutina(Long id){
        if (!odjetivoRutinaRepository.existsById(id)) {
            throw new EntityNotFoundException(("El odjetivo con id " + id + " no existe"));
        }
        odjetivoRutinaRepository.deleteById(id);
    }
}

