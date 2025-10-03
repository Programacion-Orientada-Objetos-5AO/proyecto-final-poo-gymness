package ar.edu.huergo.jsanchezortega.gymness.controller.plan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.huergo.jsanchezortega.gymness.dto.plan.ActualizarPlanDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.plan.CrearPlanDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.plan.PlanDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;
import ar.edu.huergo.jsanchezortega.gymness.mapper.plan.PlanMapper;
import ar.edu.huergo.jsanchezortega.gymness.service.plan.PlanService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/planes")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanMapper planMapper;

    @GetMapping
    public ResponseEntity<List<PlanDTO>> obtenerTodosLosPlanes() {
        List<Plan> planes = planService.obtenerTodosLosPlanes();
        return ResponseEntity.ok(planMapper.toDTOList(planes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> obtenerPlanPorId(@PathVariable("id") Long id) {
        try {
            Plan plan = planService.obtenerPlanPorId(id);
            return ResponseEntity.ok(planMapper.toDTO(plan));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<PlanDTO> crearPlan(@RequestBody CrearPlanDTO dto) {
        Plan plan = planMapper.toEntity(dto);
        Plan planCreado = planService.crearPlan(plan);
        return ResponseEntity.status(HttpStatus.CREATED).body(planMapper.toDTO(planCreado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> actualizarPlan(@PathVariable("id") Long id, @RequestBody ActualizarPlanDTO dto) {
        try {
            Plan planExistente = planService.obtenerPlanPorId(id);
            planMapper.updateEntity(planExistente, dto);
            Plan planActualizado = planService.crearPlan(planExistente); // Guardar cambios
            return ResponseEntity.ok(planMapper.toDTO(planActualizado));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable("id") Long id) {
        try {
            planService.eliminarPlan(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
