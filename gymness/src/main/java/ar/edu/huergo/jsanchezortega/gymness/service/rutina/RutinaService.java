package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.OdjetivoRutina;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Rutina;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.OdjetivoRutinaRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.RutinaRepository;
import ar.edu.huergo.jsanchezortega.gymness.service.persona.ClienteService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RutinaService {
    
    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired 
    private OdjetivoRutinaRepository odjetivoRutinaRepository;

    @Autowired  
    private SesionEntrenamientoService sesionEntrenamientoService;

    @Autowired
    private ClienteService clienteService;

    public List<Rutina>  obtenerTodasLasRutinas(){
        return rutinaRepository.findAll();
    }

    public Rutina obtenerRutinaPorId(Long id) throws EntityNotFoundException{
        return rutinaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("La rutina no fue encontrada"));
    }

    public Rutina crearRutina(Rutina rutina, Long odjetivoRutinaId, List<Long> SesionEntrenamientoIds, Long clienteId){
        if (odjetivoRutinaId != null) {
            OdjetivoRutina odjetivoRutina = odjetivoRutinaRepository.findById(odjetivoRutinaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"El odjetivo de la rutina no fue encontrado"));
            rutina.setOdjetivo(odjetivoRutina);
        }

        Cliente cliente = clienteService.resolverPlan(clienteId);
        rutina.setCliente(cliente);

        List<SesionEntrenamiento> sesionEntrenamientos = sesionEntrenamientoService.resolveSesionEntrenamientos(SesionEntrenamientoIds);

        if (!sesionEntrenamientos.isEmpty()) {
            rutina.setSesiones(sesionEntrenamientos);
        }
        return rutinaRepository.save(rutina);
    }

    public Rutina actualizarRutina(Long id, Rutina rutina, Long odjetivoRutinaId, List<Long> sesionEntrenamientoIds, Long clienteId) throws EntityNotFoundException{
        Rutina rutinaExistente = obtenerRutinaPorId(id);
        
        rutinaExistente.setNombre(rutina.getNombre());
        rutinaExistente.setDescripcion(rutina.getDescripcion());
        rutinaExistente.setFechaCreacion(rutina.getFechaCreacion());
        rutinaExistente.setCliente(rutina.getCliente());
        
        if (sesionEntrenamientoIds != null && !sesionEntrenamientoIds.isEmpty()) {
            List<SesionEntrenamiento> sesionEntrenamientos = sesionEntrenamientoService.resolveSesionEntrenamientos(sesionEntrenamientoIds);
            rutinaExistente.setSesiones(sesionEntrenamientos);
        }

        

        if (odjetivoRutinaId != null) {
            OdjetivoRutina odjetivoRutina = odjetivoRutinaRepository.findById(odjetivoRutinaId)
                    .orElseThrow(() -> new EntityNotFoundException("Odjetivo de la rutina no encontrada"));
            rutinaExistente.setOdjetivo(odjetivoRutina);;
        }

        return rutinaRepository.save(rutinaExistente);
    }

    public void eliminarRutina(Long id) throws EntityNotFoundException{
        Rutina rutina = obtenerRutinaPorId(id);
        rutinaRepository.delete(rutina);
    }

}
