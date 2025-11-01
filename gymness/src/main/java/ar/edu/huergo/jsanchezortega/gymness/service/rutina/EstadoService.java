package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Estado;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> obtenerTodosLosEstados() {
        return estadoRepository.findAll();
    }

    public Estado obtenerEstadoPorId(Long id) throws EntityNotFoundException {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));
    }

    public Estado crearEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado actualizarEstado(Long id, Estado estado) {
        Estado estadoExistente = obtenerEstadoPorId(id);
        estadoExistente.setNombre(estado.getNombre());
        return estadoRepository.save(estadoExistente);
    }

    public void eliminarEstado(Long id) throws EntityNotFoundException {
        Estado estado = obtenerEstadoPorId(id);
        estadoRepository.delete(estado);
    }

}
