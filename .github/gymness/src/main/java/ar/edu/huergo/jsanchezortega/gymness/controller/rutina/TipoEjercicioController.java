package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.TipoEjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.TipoEjercicio;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.TipoEjercicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tipos-ejercicio")
public class TipoEjercicioController {
    
    private final TipoEjercicioService tipoEjercicioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<List<TipoEjercicioDTO>> obtenerTodosLosTiposEjercicio() {
        List<TipoEjercicio> tipos = tipoEjercicioService.obtenerTodoTipoEjercicio();
        List<TipoEjercicioDTO> tiposDTO = tipos.stream()
                .map(tipo -> new TipoEjercicioDTO(tipo.getId(), tipo.getNombre()))
                .toList();
        return ResponseEntity.ok(tiposDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<TipoEjercicioDTO> obtenerTipoEjercicioPorId(@PathVariable Long id) {
        TipoEjercicio tipo = tipoEjercicioService.obtenerTipoEjercicioPorId(id);
        TipoEjercicioDTO tipoDTO = new TipoEjercicioDTO(tipo.getId(), tipo.getNombre());
        return ResponseEntity.ok(tipoDTO);
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<TipoEjercicioDTO> buscarPorNombre(@RequestParam String nombre) {
        Optional<TipoEjercicio> tipoOpt = tipoEjercicioService.obtenerTipoEjercicioPorNombre(nombre);
        if (tipoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        TipoEjercicio tipo = tipoOpt.get();
        TipoEjercicioDTO tipoDTO = new TipoEjercicioDTO(tipo.getId(), tipo.getNombre());
        return ResponseEntity.ok(tipoDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoEjercicioDTO> crearTipoEjercicio(@Valid @RequestBody TipoEjercicioDTO tipoDTO) {
        TipoEjercicio tipo = new TipoEjercicio(tipoDTO.getNombre());
        TipoEjercicio nuevoTipo = tipoEjercicioService.crearTipoEjercicio(tipo);
        TipoEjercicioDTO nuevoTipoDTO = new TipoEjercicioDTO(nuevoTipo.getId(), nuevoTipo.getNombre());
        return ResponseEntity.ok(nuevoTipoDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoEjercicioDTO> actualizarTipoEjercicio(
            @PathVariable Long id, 
            @Valid @RequestBody TipoEjercicioDTO tipoDTO) {
        TipoEjercicio tipo = new TipoEjercicio(tipoDTO.getNombre());
        TipoEjercicio tipoActualizado = tipoEjercicioService.actualizarTipoEjercicio(id, tipo);
        TipoEjercicioDTO tipoActualizadoDTO = new TipoEjercicioDTO(tipoActualizado.getId(), tipoActualizado.getNombre());
        return ResponseEntity.ok(tipoActualizadoDTO);
    }
}