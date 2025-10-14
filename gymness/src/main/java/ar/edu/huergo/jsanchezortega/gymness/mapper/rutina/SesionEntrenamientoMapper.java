package ar.edu.huergo.jsanchezortega.gymness.mapper.rutina;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.ActualizarSesionEntrenamientoDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearSesionEntrenamientoDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.EstadoDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.RutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.SesionEntrenamientoDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;

@Component
public class SesionEntrenamientoMapper {

    public SesionEntrenamiento toEntity(ActualizarSesionEntrenamientoDTO dto) {
        if (dto == null) {
            return null;
        }
        SesionEntrenamiento sesion = new SesionEntrenamiento();
        sesion.setNombre(dto.getNombre());
        sesion.setFechaRealizado(dto.getFechaRealizado());
        return sesion;
    }

    public SesionEntrenamiento toEntity(CrearSesionEntrenamientoDTO dto) {
        if (dto == null) {
            return null;
        }
        SesionEntrenamiento sesion = new SesionEntrenamiento();
        sesion.setNombre(dto.getNombre());
        sesion.setFechaRealizado(dto.getFechaRealizado());
        return sesion;
    }

    public SesionEntrenamientoDTO toDTO(SesionEntrenamiento entity) {
        if (entity == null) {
            return null;
        }

        SesionEntrenamientoDTO dto = new SesionEntrenamientoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setFechaRealizado(entity.getFechaRealizado());
        
        
        if (entity.getEstado() != null) {
            dto.setEstadoId(entity.getEstado().getId());
            EstadoDTO estadoDTO = new EstadoDTO();
            estadoDTO.setId(entity.getEstado().getId());
            estadoDTO.setNombre(entity.getEstado().getNombre());
            dto.setEstado(estadoDTO);
        }

        if (entity.getRutina() != null) {
            dto.setRutinaId(entity.getEstado().getId());
            RutinaDTO rutinaDTO = new RutinaDTO();
            rutinaDTO.setId(entity.getRutina().getId());
            rutinaDTO.setNombre(entity.getRutina().getNombre());
            dto.setRutina(rutinaDTO);
        }

        if (entity.getEjercicio() != null) {
            dto.setEjercicioIds(
                entity.getEjercicio().stream()
                    .map(ejercicioSesion -> ejercicioSesion.getEjercicio().getId())
                    .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public List<SesionEntrenamientoDTO> toDTOList(List<SesionEntrenamiento> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
