package ar.edu.huergo.jsanchezortega.gymness.controller.persona;

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
import org.springframework.web.bind.annotation.RestController;

import ar.edu.huergo.jsanchezortega.gymness.dto.persona.EspecialidadDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Especialidad;
import ar.edu.huergo.jsanchezortega.gymness.service.persona.EspecialidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/especialidad")
public class EspecialidadController {
    
    private final EspecialidadService especialidadService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<List<EspecialidadDTO>> obtenerTodas() {
        List<Especialidad> especialidades = especialidadService.obtenerTodasLasEspecialidades();
        List<EspecialidadDTO> especialidadesDTO = especialidades.stream()
                .map(e -> new EspecialidadDTO(e.getId(), e.getNombre()))
                .toList();
        return ResponseEntity.ok(especialidadesDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<EspecialidadDTO> obtenerEspecialidadPorId(@PathVariable("id") Long id) {
        Especialidad especialidad = especialidadService.obtenerEspecialidadPorId(id);
        EspecialidadDTO dto = new EspecialidadDTO(especialidad.getId(), especialidad.getNombre());
        return ResponseEntity.ok(dto);
    }

    /*
    @GetMapping("/buscar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<List<EspecialidadDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<Especialidad> especialidades = especialidadService.buscarPorNombre(nombre);
        List<EspecialidadDTO> especialidadesDTO = especialidades.stream()
                .map(e -> new EspecialidadDTO(e.getId(), e.getNombre()))
                .toList();
        return ResponseEntity.ok(especialidadesDTO);
    }
     */
    

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EspecialidadDTO> crearEspecialidad(@Valid @RequestBody EspecialidadDTO dto) {
        Especialidad especialidad = new Especialidad(dto.getNombre());
        Especialidad nueva = especialidadService.crearEspecialidad(especialidad);
        EspecialidadDTO nuevaDTO = new EspecialidadDTO(nueva.getId(), nueva.getNombre());
        return ResponseEntity.ok(nuevaDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EspecialidadDTO> actualizarEspecialidad(@PathVariable("id") Long id,@Valid @RequestBody EspecialidadDTO dto) {
        Especialidad especialidad = new Especialidad(dto.getNombre());
        Especialidad actualizada = especialidadService.actualizarEspecialidad(id, especialidad);
        EspecialidadDTO actualizadaDTO = new EspecialidadDTO(actualizada.getId(), actualizada.getNombre());
        return ResponseEntity.ok(actualizadaDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarEspecialidad(@PathVariable("id") Long id) {
        especialidadService.eliminarEspecialidad(id);
        return ResponseEntity.noContent().build();
    }

}
