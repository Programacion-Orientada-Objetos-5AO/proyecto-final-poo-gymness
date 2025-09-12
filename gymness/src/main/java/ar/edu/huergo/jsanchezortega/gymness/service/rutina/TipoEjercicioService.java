package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.TipoEjercicio;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.TipoEjercicioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoEjercicioService {
 
    @Autowired
    private TipoEjercicioRepository tipoEjercicioRepository;

    public List<TipoEjercicio> obtenerTodoTipoEjercicio(){
        return tipoEjercicioRepository.findAll();
    }

    public TipoEjercicio obtenerTipoEjercicioPorId(Long id) throws EntityNotFoundException{
        return tipoEjercicioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tipo de ejercicio no encontrado"));
    }

    public TipoEjercicio crearTipoEjercicio(TipoEjercicio tipo){
        return tipoEjercicioRepository.save(tipo);
    }

    public TipoEjercicio actualizarTipoEjercicio(Long id, TipoEjercicio tipo){
        TipoEjercicio tipoEjercicioExistente = obtenerTipoEjercicioPorId(id);
        tipoEjercicioExistente.setNombre(tipo.getNombre());
        return tipoEjercicioRepository.save(tipoEjercicioExistente);
    }

    public Optional<TipoEjercicio> obtenerTipoEjercicioPorNombre(String nombre){
        return tipoEjercicioRepository.findByNombre(nombre);
    }

    public void eliminarTipoEjercicio(Long id) {
        if (!tipoEjercicioRepository.existsById(id)) {
            throw new EntityNotFoundException("El músculo con id " + id + " no existe");
        }
        tipoEjercicioRepository.deleteById(id);
    }


    public List<TipoEjercicio> resolverTipo(List<Long> tipoEjercicioIds) throws IllegalArgumentException, EntityNotFoundException {
        if (tipoEjercicioIds == null || tipoEjercicioIds.isEmpty()) {
            throw new IllegalArgumentException("Debe especificar al menos un ingrediente");
        }
        List<TipoEjercicio> tipoEjercicios = tipoEjercicioRepository.findAllById(tipoEjercicioIds);
        if (tipoEjercicios.size() != tipoEjercicioIds.stream().filter(Objects::nonNull).distinct()
                .count()) {
            throw new EntityNotFoundException("Uno o más ingredientes no existen");
        }
        return tipoEjercicios;
    }
}
