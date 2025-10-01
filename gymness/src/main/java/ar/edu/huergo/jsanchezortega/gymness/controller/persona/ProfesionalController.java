package ar.edu.huergo.jsanchezortega.gymness.controller.persona;

import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ActualizarProfesionalDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.CrearProfesionalDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ProfesionalDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Profesional;
import ar.edu.huergo.jsanchezortega.gymness.mapper.persona.ProfesionalMapper;
import ar.edu.huergo.jsanchezortega.gymness.service.persona.ProfesionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profesionales")
public class ProfesionalController {

    private final ProfesionalService profesionalService;
    private final ProfesionalMapper profesionalMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<List<ProfesionalDTO>> obtenerTodos() {
        List<Profesional> profesionales = profesionalService.obtenerTodosLosProfesionales();
        return ResponseEntity.ok(profesionalMapper.toDTOList(profesionales));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<ProfesionalDTO> obtenerPorId(@PathVariable("id") Long id) {
        Profesional profesional = profesionalService.obtenerProfesionalPorId(id);
        return ResponseEntity.ok(profesionalMapper.toDTO(profesional));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfesionalDTO> crearProfesional(@Valid @RequestBody CrearProfesionalDTO dto) {
        Profesional profesional = profesionalMapper.toEntity(dto);
        Profesional nuevo = profesionalService.crearProfesional(profesional, dto.getEspecialidadId());
        return ResponseEntity.ok(profesionalMapper.toDTO(nuevo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfesionalDTO> actualizar(@PathVariable("id") Long id,
                                                    @Valid @RequestBody ActualizarProfesionalDTO dto) {
        Profesional profesional = profesionalMapper.toEntity(dto);
        Profesional actualizado = profesionalService.actualizarProfesional(id, profesional, dto.getEspecialidadId());
        return ResponseEntity.ok(profesionalMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        profesionalService.eliminarProfesional(id);
        return ResponseEntity.noContent().build();
    }
}
