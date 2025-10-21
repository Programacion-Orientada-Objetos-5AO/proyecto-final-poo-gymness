package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearRutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.RutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Rutina;
import ar.edu.huergo.jsanchezortega.gymness.mapper.rutina.RutinaMapper;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.RutinaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rutinas")
public class RutinaController {

    
    private final RutinaService rutinaService;
    private final RutinaMapper rutinaMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE') or hasRole('PROFESIONAL')")
    public ResponseEntity<List<RutinaDTO>> obtenerTodas() {
        List<Rutina> rutinas = rutinaService.obtenerTodasLasRutinas();
        return ResponseEntity.ok(rutinaMapper.toDTOList(rutinas));
    }

    @GetMapping("/{id}")
     @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE') or hasRole('PROFESIONAL')")
    public ResponseEntity<RutinaDTO> obtenerPorId(@PathVariable ("id") Long id) {
        Rutina rutina = rutinaService.obtenerRutinaPorId(id);
        return ResponseEntity.ok(rutinaMapper.toDTO(rutina));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESIONAL')")
    public ResponseEntity<RutinaDTO> crearRutina(@RequestBody CrearRutinaDTO dto, Cliente cliente) {
        Rutina Rutina = rutinaMapper.toEntity(dto, cliente);
        Rutina nuevo = rutinaService.crearRutina(Rutina, dto.getObjetivoId(),dto.getSesionEntrenamientoIds(),dto.getClienteId());
        return ResponseEntity.ok(rutinaMapper.toDTO(nuevo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RutinaDTO> actualizarRutina(@PathVariable ("id") Long id, @RequestBody RutinaDTO dto, Long clienteId) {
        Rutina rutina = rutinaMapper.toEntity(dto);
        Rutina rutinaActualizada = rutinaService.actualizarRutina(id, rutina, dto.getObjetivoId(), dto.getSesionEntrenamientoIds(), dto.getClienteId());
        return ResponseEntity.ok(rutinaMapper.toDTO(rutinaActualizada));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarRutina(@PathVariable ("id") Long id) {
        rutinaService.eliminarRutina(id);
        return ResponseEntity.noContent().build();
    }
}
