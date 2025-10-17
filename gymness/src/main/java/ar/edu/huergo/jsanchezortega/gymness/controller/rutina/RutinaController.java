package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Rutina;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.RutinaService;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;

    @GetMapping
    public ResponseEntity<List<Rutina>> obtenerTodas() {
        List<Rutina> rutinas = rutinaService.obtenerTodasLasRutinas();
        return ResponseEntity.ok(rutinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> obtenerPorId(@PathVariable Long id) {
        Rutina rutina = rutinaService.obtenerRutinaPorId(id);
        return ResponseEntity.ok(rutina);
    }

    @PostMapping
    public ResponseEntity<Rutina> crearRutina(
            @RequestBody Rutina rutina,
            @RequestParam(name = "odjetivoRutinaId", required = false) Long odjetivoRutinaId,
            @RequestParam(name = "sesionEntrenamientoIds", required = false) List<Long> sesionEntrenamientoIds) {

        Rutina nuevaRutina = rutinaService.crearRutina(rutina, odjetivoRutinaId, sesionEntrenamientoIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRutina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rutina> actualizarRutina(
            @PathVariable Long id,
            @RequestBody Rutina rutina,
            Long odjetivoRutinaId,
            List<Long> sesionEntrenamientoIds) {

        Rutina rutinaActualizada = rutinaService.actualizarRutina(id, rutina, odjetivoRutinaId, sesionEntrenamientoIds);
        return ResponseEntity.ok(rutinaActualizada);
    }

    public ResponseEntity<Void> eliminarRutina(@PathVariable Long id) {
        rutinaService.eliminarRutina(id);
        return ResponseEntity.noContent().build();
    }
}
