package ar.edu.huergo.jsanchezortega.gymness.mapper.plan;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.plan.ActualizarPlanDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.plan.CrearPlanDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.plan.PlanDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;

@Component
public class PlanMapper {

    public Plan toEntity(PlanDTO dto) {
        if (dto == null) {
            return null;
        }
        Plan plan = new Plan();
        plan.setId(dto.getId());
        plan.setNombre(dto.getNombre());
        plan.setDescripcion(dto.getDescripcion());
        plan.setPrecio(dto.getPrecio());
        return plan;
    }

    public Plan toEntity(CrearPlanDTO dto) {
        if (dto == null) {
            return null;
        }
        Plan plan = new Plan();
        plan.setNombre(dto.getNombre());
        plan.setDescripcion(dto.getDescripcion());
        plan.setPrecio(dto.getPrecio());
        return plan;
    }

    public PlanDTO toDTO(Plan entity) {
        if (entity == null) {
            return null;
        }

        PlanDTO dto = new PlanDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
        
        if (entity.getCliente() != null) {
            dto.setClienteIds(List.of(entity.getCliente().getId()));
        }

        if (entity.getProfesionales() != null) {
            dto.setProfesionalIds(entity.getProfesionales().stream()
                    .map(profesional -> profesional.getId())
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }

    public void updateEntity(Plan entity, ActualizarPlanDTO dto) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }
        if (dto.getDescripcion() != null) {
            entity.setDescripcion(dto.getDescripcion());
        }
        if (dto.getPrecio() != null) {
            entity.setPrecio(dto.getPrecio());
        }
    }

    public List<PlanDTO> toDTOList(List<Plan> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
