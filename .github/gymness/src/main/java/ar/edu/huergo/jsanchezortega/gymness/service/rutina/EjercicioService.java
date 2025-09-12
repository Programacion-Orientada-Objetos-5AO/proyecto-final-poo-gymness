package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.MusculoObjetivo;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.TipoEjercicio;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.EjercicioRepository;
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
        List<MusculoObjetivo> musculoObjetivos = musculoObjetivosService.resolverMusculoObjetivos(musculoObjetivoIds);

        if (!tipoEjercicios.isEmpty()) {
            ejercicio.setTipoEjercicio(tipoEjercicios.get(0));
        }

        ejercicio.setMusculosObjetivo(musculoObjetivos);
        return ejercicioRepository.save(ejercicio);
    }

    public Ejercicio actualizarEjercicio(Long id, Ejercicio ejercicio, List<Long> tipoEjercicioIds, List<Long> musculoObjetivoIds ){
        Ejercicio ejercicioExistente = obtenerEjercicioId(id);
        ejercicioExistente.setNombre(ejercicio.getNombre());
        ejercicioExistente.setDescripcion(ejercicio.getDescripcion());
        ejercicioExistente.setInstrucciones(ejercicio.getInstrucciones());
        ejercicioExistente.setVideoUrl(ejercicio.getVideoUrl());
        ejercicioExistente.setImagenUrl(ejercicio.getImagenUrl());
        ejercicioExistente.setActivo(ejercicio.getActivo());

        if (tipoEjercicioIds != null && !tipoEjercicioIds.isEmpty()) {
            List<TipoEjercicio> tipoEjercicios = tipoEjercicioService.resolverTipo(tipoEjercicioIds);
            ejercicioExistente.setTipoEjercicio(tipoEjercicios.get(0)); 
        }

        if (musculoObjetivoIds != null && !musculoObjetivoIds.isEmpty()) {
            List<MusculoObjetivo> musculoObjetivos = musculoObjetivosService.resolverMusculoObjetivos(musculoObjetivoIds);
            ejercicioExistente.setMusculosObjetivo(musculoObjetivos);
        }

        return ejercicioRepository.save(ejercicioExistente);

        }

        public void eliminarEjercicio(Long id) throws EntityNotFoundException {
        Ejercicio ejercicio = obtenerEjercicioId(id);
        ejercicioRepository.delete(ejercicio);
        }
}
