package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.EstadoDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Estado;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.EstadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<List<EstadoDTO>> obtenerTodosLosEstados() {
        List<Estado> estados = estadoService.obtenerTodosLosEstados();
        List<EstadoDTO> estadoDTOs = estados.stream().
            map(estado -> new EstadoDTO(estado.getId(), estado.getNombre()))
            .toList();
        return ResponseEntity.ok(estadoDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<EstadoDTO> obtenerEstadoPorId(@PathVariable ("id") Long id) {
        Estado estado = estadoService.obtenerEstadoPorId(id);
        EstadoDTO estadoDTO = new EstadoDTO(estado.getId(), estado.getNombre());
        return ResponseEntity.ok(estadoDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstadoDTO> crearEstado(@Valid @RequestBody EstadoDTO estadoDTO) {
        Estado estado = new Estado(estadoDTO.getNombre());
        Estado nuevoEstado = estadoService.crearEstado(estado);
        EstadoDTO nuevoEstadoDTO = new EstadoDTO(nuevoEstado.getId(), nuevoEstado.getNombre());
        return ResponseEntity.ok(nuevoEstadoDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstadoDTO> actualizarEstado(
            @PathVariable ("id") Long id,
            @Valid @RequestBody EstadoDTO estadoDTO) {
            Estado estado = new Estado(estadoDTO.getNombre());
            Estado estadoActualizado = estadoService.actualizarEstado(id, estado);
            EstadoDTO estadoActualizadoDTO = new EstadoDTO(estadoActualizado.getId(), estadoActualizado.getNombre());
            return ResponseEntity.ok(estadoActualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable ("id") Long id) {
        estadoService.eliminarEstado(id);
        return ResponseEntity.noContent().build();
    }
}
