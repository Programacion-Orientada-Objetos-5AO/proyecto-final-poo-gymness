package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.MusculoObjetivo;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.MusculoObjetivoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MusculoOdjetivoService {
    
    @Autowired
    private MusculoObjetivoRepository musculoObjetivoRepository;

    public List<MusculoObjetivo> obtenerTodoMusculoObjetivos(){
        return musculoObjetivoRepository.findAll();
    }

    public MusculoObjetivo crearMusculoObjetivo(MusculoObjetivo musculo){
        return musculoObjetivoRepository.save(musculo);
    }

    public MusculoObjetivo obtemerMusculoObjetivoId(Long id) throws EntityNotFoundException{
            return musculoObjetivoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("musculo no encontrado"));
    }

    public MusculoObjetivo actualizarTipoEjercicio(Long id, MusculoObjetivo musculo){
        MusculoObjetivo musculoObjetivoExistente = obtemerMusculoObjetivoId(id);
        musculoObjetivoExistente.setNombre(musculo.getNombre());
        return musculoObjetivoRepository.save(musculoObjetivoExistente);
    }

    public Optional<MusculoObjetivo> obtenerTipoEjercicioPorNombre(String nombre){
        return musculoObjetivoRepository.findByNombre(nombre);
    }

    public List<MusculoObjetivo> resolverIngredientes(List<Long> musculoObjetivoIds) throws IllegalArgumentException, EntityNotFoundException {
        if (musculoObjetivoIds == null || musculoObjetivoIds.isEmpty()) {
            throw new IllegalArgumentException("Debe especificar al menos un ingrediente");
        }
        List<MusculoObjetivo> MusculoObjetivos = musculoObjetivoRepository.findAllById(musculoObjetivoIds);
        if (MusculoObjetivos.size() != musculoObjetivoIds.stream().filter(Objects::nonNull).distinct()
                .count()) {
            throw new EntityNotFoundException("Uno o más ingredientes no existen");
        }
        return MusculoObjetivos;
    }
}
