package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.ActualizarSesionEntrenamientoDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearSesionEntrenamientoDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.SesionEntrenamientoDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.mapper.rutina.SesionEntrenamientoMapper;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.SesionEntrenamientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sesiones")
public class SesionEntrenamientoController {

    private final SesionEntrenamientoService sesionService;    
    private final SesionEntrenamientoMapper sesionEntrenamientoMapper;
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<List<SesionEntrenamientoDTO>> obtenerTodasLasSesiones() {
        List<SesionEntrenamiento> sesiones = sesionService.obtenerTodasLasSesiones();
        return ResponseEntity.ok(sesionEntrenamientoMapper.toDTOList(sesiones));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<SesionEntrenamientoDTO> obtenerSesionesPorId(@PathVariable ("id") Long id){
        SesionEntrenamiento sesionEntrenamiento = sesionService.obtenerSesionPorId(id);
        return ResponseEntity.ok(sesionEntrenamientoMapper.toDTO(sesionEntrenamiento));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<SesionEntrenamientoDTO> crearSesion(@Valid @RequestBody CrearSesionEntrenamientoDTO dto){
        SesionEntrenamiento sesion = sesionEntrenamientoMapper.toEntity(dto);
        SesionEntrenamiento nuevo = sesionService.crearSesion(sesion, dto.getRutinaId(), dto.getEstadoId(), dto.getEjercicioIds());
        return ResponseEntity.ok(sesionEntrenamientoMapper.toDTO(nuevo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SesionEntrenamientoDTO> actualizarSesion(@PathVariable("id") Long id, @Valid @RequestBody ActualizarSesionEntrenamientoDTO dto){
        SesionEntrenamiento sesion = sesionEntrenamientoMapper.toEntity(dto);
        SesionEntrenamiento actualizado = sesionService.actualizarSesion(id, sesion, dto.getRutinaId(), dto.getEstadoId(), dto.getEjercicioIds());
        return ResponseEntity.ok(sesionEntrenamientoMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        sesionService.eliminarSesion(id);
        return ResponseEntity.noContent().build();
    }
}
