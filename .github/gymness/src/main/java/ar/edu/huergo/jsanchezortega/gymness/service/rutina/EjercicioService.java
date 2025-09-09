package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.MusculoObjetivo;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.TipoEjercicio;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.EjercicioRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.MusculoObjetivoRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.TipoEjercicioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EjercicioService {
    
    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private MusculoOdjetivoService musculoObjetivosService;

    @Autowired
    private TipoEjercicioService tipoEjercicioService;

    public List<Ejercicio> obtenerTodosLosEjercicios(){
        return ejercicioRepository.findAll();
    }

    public Ejercicio obtenerEjercicioId(Long id) throws EntityNotFoundException{ 
        return ejercicioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado"));
    }

    public Ejercicio crearEjercicio(Ejercicio ejercicio, List<Long> tipoEjercicioIds, List<Long> musculoObjetivoIds){
        List<TipoEjercicio> tipoEjercicios = tipoEjercicioService.resolverTipo(tipoEjercicioIds);
        List<MusculoObjetivo> musculoObjetivos = musculoObjetivosService.resolverIngredientes(musculoObjetivoIds);
        ejercicio.setMusculoObjetivo(musculoObjetivos);
        ejercicio.setTipoEjercicio(tipoEjercicios);
    }
}
