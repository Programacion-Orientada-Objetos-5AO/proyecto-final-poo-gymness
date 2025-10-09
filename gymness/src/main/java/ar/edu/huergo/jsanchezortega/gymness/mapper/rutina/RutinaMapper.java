package ar.edu.huergo.jsanchezortega.gymness.mapper.rutina;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearRutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.OdjetivoRutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.RutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Rutina;

@Component
public class RutinaMapper {

    public Rutina toEntity(RutinaDTO dto) {
        if (dto == null) {
            return null;
        }
        Rutina rutina = new Rutina();
        rutina.setId(dto.getId());
        rutina.setNombre(dto.getNombre());
        rutina.setDescripcion(dto.getDescripcion());
        rutina.setFechaCreacion(dto.getFechaCreacion());
        return rutina;
    }

    public Rutina toEntity(CrearRutinaDTO dto) {
        if (dto == null) {
            return null;
        }
        Rutina rutina = new Rutina();
        rutina.setNombre(dto.getNombre());
        rutina.setDescripcion(dto.getDescripcion());
        rutina.setFechaCreacion(LocalDateTime.now());
        return rutina;
    }

    public RutinaDTO toDTO(Rutina entity) {
        if (entity == null) {
            return null;
        }

        RutinaDTO dto = new RutinaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaCreacion(entity.getFechaCreacion());
        
        if (entity.getOdjetivo() != null) {
            dto.setObjetivoId(entity.getOdjetivo().getId());
            OdjetivoRutinaDTO objetivoDTO = new OdjetivoRutinaDTO();
            objetivoDTO.setId(entity.getOdjetivo().getId());
            objetivoDTO.setNombre(entity.getOdjetivo().getNombre());
        }

        if (entity.getSesiones() != null ) {
            dto.setSesiones(entity.getSesiones().stream()
                .map(new SesionEntrenamientoMapper()::toDTO)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }

    public List<RutinaDTO> toDTOList(List<Rutina> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
