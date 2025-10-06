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
import org.springframework.web.bind.annotation.RestController;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.OdjetivoRutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.OdjetivoRutina;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.OdjetivoRutinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/odjetivo-rutina")
public class OdjetivoRutinaController {
    
    private final OdjetivoRutinaService odjetivoRutinaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE') or hasRole('PROFESIONAL')")
    public ResponseEntity<List<OdjetivoRutinaDTO>> obtenerTodosLosOdjeticosRutinas(){
        List<OdjetivoRutina> odjetivos = odjetivoRutinaService.obtenerTodoLosOdjetivoRutinas();
        List<OdjetivoRutinaDTO> odjetivosDTO = odjetivos.stream()
            .map(odjetivo -> new OdjetivoRutinaDTO(odjetivo.getId(), odjetivo.getNombre()))
            .toList();
        return ResponseEntity.ok(odjetivosDTO);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE') or hasRole('PROFESIONAL')")
    public ResponseEntity<OdjetivoRutinaDTO> obtenerOdjetivoRutinaPorId(@PathVariable ("id") Long id){
        OdjetivoRutina odjetivo = odjetivoRutinaService.obteneOdjetivoRutinaPorId(id);
        OdjetivoRutinaDTO odjetivoDTO = new OdjetivoRutinaDTO(odjetivo.getId(), odjetivo.getNombre());
        return ResponseEntity.ok(odjetivoDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<OdjetivoRutinaDTO> crearObjetivoRutina(@Valid @RequestBody OdjetivoRutinaDTO odjetivoDTO){
        OdjetivoRutina odjetivo = new OdjetivoRutina(odjetivoDTO.getNombre());
        OdjetivoRutina nuevoOdjetivo = odjetivoRutinaService.crearOdjetivoRutina(odjetivo);
        OdjetivoRutinaDTO nuevoOdjetivoDTO = new OdjetivoRutinaDTO(nuevoOdjetivo.getId(), nuevoOdjetivo.getNombre());
        return ResponseEntity.ok(nuevoOdjetivoDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OdjetivoRutinaDTO> actualizarOdjetivoRutina(@PathVariable ("id") Long id, @Valid @RequestBody OdjetivoRutinaDTO odjetivoDTO){
        OdjetivoRutina odjetivo = new OdjetivoRutina(odjetivoDTO.getNombre());
        OdjetivoRutina nuevoOdjetivo = odjetivoRutinaService.actualizarOdjetivoRutina(id, odjetivo);
        OdjetivoRutinaDTO actualizarOdjetivoDTO = new OdjetivoRutinaDTO(nuevoOdjetivo.getId(), nuevoOdjetivo.getNombre());
        return ResponseEntity.ok(actualizarOdjetivoDTO);
    }

     @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable ("id") Long id) {
        odjetivoRutinaService.obteneOdjetivoRutinaPorId(id); 
        return ResponseEntity.noContent().build();
    }




}

