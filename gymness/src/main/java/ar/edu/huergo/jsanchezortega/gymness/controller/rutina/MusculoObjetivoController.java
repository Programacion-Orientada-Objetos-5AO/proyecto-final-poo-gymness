
package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.MusculoOdjetivoDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.MusculoObjetivo;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.MusculoOdjetivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/musculos-objetivo")
public class MusculoObjetivoController {
    
    private final MusculoOdjetivoService musculoObjetivoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<List<MusculoOdjetivoDTO>> obtenerTodosLosMusculosObjetivo() {
        List<MusculoObjetivo> musculos = musculoObjetivoService.obtenerTodoMusculoObjetivos();
        List<MusculoOdjetivoDTO> musculosDTO = musculos.stream()
                .map(musculo -> new MusculoOdjetivoDTO(musculo.getId(), musculo.getNombre()))
                .toList();
        return ResponseEntity.ok(musculosDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<MusculoOdjetivoDTO> obtenerMusculoObjetivoPorId(@PathVariable Long id) {
        MusculoObjetivo musculo = musculoObjetivoService.obtemerMusculoObjetivoId(id);
        MusculoOdjetivoDTO musculoDTO = new MusculoOdjetivoDTO(musculo.getId(), musculo.getNombre());
        return ResponseEntity.ok(musculoDTO);
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<List<MusculoOdjetivoDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<MusculoObjetivo> musculos = musculoObjetivoService.obtenerTipoEjercicioPorNombre(nombre);
        List<MusculoOdjetivoDTO> musculosDTO = musculos.stream()
                .map(musculo -> new MusculoOdjetivoDTO(musculo.getId(), musculo.getNombre()))
                .toList();
        return ResponseEntity.ok(musculosDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MusculoOdjetivoDTO> crearMusculoObjetivo(@Valid @RequestBody MusculoOdjetivoDTO musculoDTO) {
        MusculoObjetivo musculo = new MusculoObjetivo(musculoDTO.getNombre());
        MusculoObjetivo nuevoMusculo = musculoObjetivoService.crearMusculoObjetivo(musculo);
        MusculoOdjetivoDTO nuevoMusculoDTO = new MusculoOdjetivoDTO(nuevoMusculo.getId(), nuevoMusculo.getNombre());
        return ResponseEntity.ok(nuevoMusculoDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MusculoOdjetivoDTO> actualizarMusculoObjetivo(
            @PathVariable Long id, 
            @Valid @RequestBody MusculoOdjetivoDTO musculoDTO) {
        MusculoObjetivo musculo = new MusculoObjetivo(musculoDTO.getNombre());
        MusculoObjetivo musculoActualizado = musculoObjetivoService.actualizarTipoEjercicio(id, musculo);
        MusculoOdjetivoDTO musculoActualizadoDTO = new MusculoOdjetivoDTO(musculoActualizado.getId(), musculoActualizado.getNombre());
        return ResponseEntity.ok(musculoActualizadoDTO);
        }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarMusculoObjetivo(@PathVariable Long id) {
        musculoObjetivoService.eliminarMusculoObjetivo(id);
        return ResponseEntity.noContent().build();
    }
}