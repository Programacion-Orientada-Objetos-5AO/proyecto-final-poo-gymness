package ar.edu.huergo.jsanchezortega.gymness.mapper.rutina;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearSesionEntrenamiento;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.EstadoDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.SesionEntrenamientoDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;

@Component
public class SesionEntrenamientoMapper {

    public SesionEntrenamiento toEntity(SesionEntrenamientoDTO dto) {
        if (dto == null) {
            return null;
        }
        SesionEntrenamiento sesion = new SesionEntrenamiento();
        sesion.setId(dto.getId());
        sesion.setFechaRealizado(dto.getFechaRealizado());
        return sesion;
    }

    public SesionEntrenamiento toEntity(CrearSesionEntrenamiento dto) {
        if (dto == null) {
            return null;
        }
        SesionEntrenamiento sesion = new SesionEntrenamiento();
        sesion.setFechaRealizado(dto.getFechaRealizado());
        return sesion;
    }

    public SesionEntrenamientoDTO toDTO(SesionEntrenamiento entity) {
        if (entity == null) {
            return null;
        }

        SesionEntrenamientoDTO dto = new SesionEntrenamientoDTO();
        dto.setId(entity.getId());
        dto.setFechaRealizado(entity.getFechaRealizado());
        
        
        if (entity.getEstado() != null) {
            dto.setEstadoId(entity.getEstado().getId());
            EstadoDTO estadoDTO = new EstadoDTO();
            estadoDTO.setId(entity.getEstado().getId());
            estadoDTO.setNombre(entity.getEstado().getNombre());
            dto.setEstado(estadoDTO);
        }
        
        return dto;
    }

    public List<SesionEntrenamientoDTO> toDTOList(List<SesionEntrenamiento> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
