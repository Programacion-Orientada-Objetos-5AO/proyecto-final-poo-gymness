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

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearEjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.EjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import ar.edu.huergo.jsanchezortega.gymness.mapper.rutina.EjercicioMapper;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.EjercicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ejercicios")
public class EjercicioController {
    
    private final EjercicioService ejercicioService;
    private final EjercicioMapper ejercicioMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<List<EjercicioDTO>> obtenerTodosLosEjercicios() {
        List<Ejercicio> ejercicios = ejercicioService.obtenerTodosLosEjercicios();
        List<EjercicioDTO> ejercicioDTOs = ejercicioMapper.toDTOList(ejercicios);
        return ResponseEntity.ok(ejercicioDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<EjercicioDTO> obtenerEjercicioPorId(@PathVariable Long id) {
        Ejercicio ejercicio = ejercicioService.obtenerEjercicioId(id);
        EjercicioDTO ejercicioDTO = ejercicioMapper.toDTO(ejercicio);
        return ResponseEntity.ok(ejercicioDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EjercicioDTO> crearEjercicio(@Valid @RequestBody CrearEjercicioDTO crearEjercicioDTO) {
        
        Ejercicio ejercicio = ejercicioMapper.toEntity(crearEjercicioDTO);
        Ejercicio nuevoEjercicio = ejercicioService.crearEjercicio(
            ejercicio,
            crearEjercicioDTO.getTipoEjercicioIds(),
            crearEjercicioDTO.getMusculosObjetivoIds()  
        );
        EjercicioDTO ejercicioDTO = ejercicioMapper.toDTO(nuevoEjercicio);
        return ResponseEntity.ok(ejercicioDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EjercicioDTO> actualizarEjercicio(
            @PathVariable Long id,
            @Valid @RequestBody EjercicioDTO ejercicioDTO,
            @RequestParam(required = false) List<Long> tipoEjercicioIds,
            @RequestParam(required = false) List<Long> musculoObjetivoIds) {
        
        Ejercicio ejercicio = ejercicioMapper.toEntity(ejercicioDTO);
        Ejercicio ejercicioActualizado = ejercicioService.actualizarEjercicio(id, ejercicio, tipoEjercicioIds, musculoObjetivoIds);
        EjercicioDTO ejercicioActualizadoDTO = ejercicioMapper.toDTO(ejercicioActualizado);
        return ResponseEntity.ok(ejercicioActualizadoDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarEjercicio(@PathVariable Long id) {
        ejercicioService.eliminarEjercicio(id);
        return ResponseEntity.noContent().build();
    }
}