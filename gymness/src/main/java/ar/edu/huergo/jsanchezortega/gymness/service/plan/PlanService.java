package ar.edu.huergo.jsanchezortega.gymness.service.plan;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;
import ar.edu.huergo.jsanchezortega.gymness.repository.plan.PlanRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public List<Plan> obtenerTodosLosPlanes(){
        return planRepository.findAll();
    }

    public Plan obtenerPlanPorId(Long id) throws EntityNotFoundException{
        return planRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Plan no encontrado"));
    }

    public Plan crearPlan(Plan plan){
        return planRepository.save(plan);
    }

    public Plan ActualizarPlan(Long id, Plan plan) throws EntityNotFoundException{
        Plan planExistente = obtenerPlanPorId(id);
        planExistente.setNombre(plan.getNombre());
        planExistente.setDescripcion(plan.getDescripcion());
        planExistente.setPrecio(plan.getPrecio());
        return plan;
    }
    
    public void eliminarPlan(Long id) throws EntityNotFoundException{
        Plan plan = obtenerPlanPorId(id);
        planRepository.delete(plan);
    }

    public Plan resolverPlan(Long planId) throws IllegalArgumentException, EntityNotFoundException {
        if (planId == null) {
            throw new IllegalArgumentException("El ID del plan no puede ser nulo");
        }

        return planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("El plan con ID " + planId + " no existe"));
    }

    public List<Plan> resolverPlanes(List<Long> planIds) {
    if (planIds == null || planIds.isEmpty()) {
        return List.of();
    }

    List<Plan> planes = planRepository.findAllById(planIds);

    if (planes.size() != planIds.stream().filter(Objects::nonNull).distinct().count()) {
        throw new EntityNotFoundException("Uno o más planes no existen");
    }

    return planes;
}
}
