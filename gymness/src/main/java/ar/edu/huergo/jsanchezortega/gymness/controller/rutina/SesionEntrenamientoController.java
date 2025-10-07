package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearSesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.SesionEntrenamientoDTO;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.SesionEntrenamientoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sesiones-entrenamiento")
public class SesionEntrenamientoController {

    @Autowired
    private SesionEntrenamientoService sesionEntrenamientoService;

    @GetMapping
    public ResponseEntity<List<SesionEntrenamientoDTO>> obtenerTodasLasSesiones() {
        List<SesionEntrenamientoDTO> sesiones = sesionEntrenamientoService.obtenerTodasLasSesiones();
        return ResponseEntity.ok(sesiones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionEntrenamientoDTO> obtenerSesionPorId(@PathVariable Long id) {
        try {
            SesionEntrenamientoDTO sesion = sesionEntrenamientoService.obtenerSesionPorId(id);
            return ResponseEntity.ok(sesion);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SesionEntrenamientoDTO> crearSesion(@Valid @RequestBody CrearSesionEntrenamiento dto) {
        try {
            SesionEntrenamientoDTO sesionCreada = sesionEntrenamientoService.crearSesion(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(sesionCreada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionEntrenamientoDTO> actualizarSesion(
            @PathVariable Long id,
            @Valid @RequestBody SesionEntrenamientoDTO dto) {
        try {
            SesionEntrenamientoDTO sesionActualizada = sesionEntrenamientoService.actualizarSesion(id, dto);
            return ResponseEntity.ok(sesionActualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSesion(@PathVariable Long id) {
        try {
            sesionEntrenamientoService.eliminarSesion(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}