package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.ActualizarEjercicioSesionDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearEjercicioSesionDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.EjercicioSesionDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.EjercicioSesion;
import ar.edu.huergo.jsanchezortega.gymness.mapper.rutina.EjercicioSesionMapper;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.EjercicioSesionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ejercicio-sesion")
public class EjercicioSesionController {

    private final EjercicioSesionService ejercicioSesionService;
    private final EjercicioSesionMapper ejercicioSesionMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL') or hasRole('USUARIO')")
    public ResponseEntity<List<EjercicioSesionDTO>> obtenerTodos() {
        List<EjercicioSesion> ejercicios = ejercicioSesionService.obtenerTodosLosEjerciciosSesion();
        return ResponseEntity.ok(ejercicioSesionMapper.toDTOList(ejercicios));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL') or hasRole('USUARIO')")
    public ResponseEntity<EjercicioSesionDTO> obtenerPorId(@PathVariable("id") Long id) {
        EjercicioSesion ejercicio = ejercicioSesionService.obtenerEjercicioSesionId(id);
        return ResponseEntity.ok(ejercicioSesionMapper.toDTO(ejercicio));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<EjercicioSesionDTO> crear(@Valid @RequestBody CrearEjercicioSesionDTO dto) {
        EjercicioSesion ejercicio = ejercicioSesionMapper.toEntity(dto);
        EjercicioSesion nuevo = ejercicioSesionService.crearEjercicioSesion(ejercicio, dto.getSesionId(), dto.getEjercicioId());
        return ResponseEntity.ok(ejercicioSesionMapper.toDTO(nuevo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<EjercicioSesionDTO> actualizar(@PathVariable("id") Long id,
                                                         @Valid @RequestBody ActualizarEjercicioSesionDTO dto) {
        EjercicioSesion ejercicio = ejercicioSesionMapper.toEntity(dto);
        EjercicioSesion actualizado = ejercicioSesionService.actualizarEjercicioSesion(id, ejercicio, dto.getSesionId(), dto.getEjercicioId());
        return ResponseEntity.ok(ejercicioSesionMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        ejercicioSesionService.eliminarEjercicioSesion(id);
        return ResponseEntity.noContent().build();
    }

}