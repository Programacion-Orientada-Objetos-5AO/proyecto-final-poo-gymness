package ar.edu.huergo.jsanchezortega.gymness.mapper.rutina;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.ActualizarEjercicioSesionDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearEjercicioSesionDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.EjercicioSesionDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.EjercicioSesion;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EjercicioSesionMapper {

    // CrearEjercicioSesionDTO → Entidad
    public EjercicioSesion toEntity(CrearEjercicioSesionDTO dto) {
        if (dto == null) {
            return null;
        }

        EjercicioSesion entity = new EjercicioSesion();
        entity.setDuracion(dto.getDuracion());
        entity.setCaloriasQuemadas(dto.getCaloriasQuemadas());
        entity.setSeries(dto.getSeries());
        entity.setRepeticiones(dto.getRepeticiones());
        entity.setPesoUtilizado(dto.getPesoUtilizado());
        entity.setRir(dto.getRir());

        // Relación con otras entidades
        if (dto.getSesionId() != null) {
            SesionEntrenamiento sesion = new SesionEntrenamiento();
            sesion.setId(dto.getSesionId());
            entity.setSesion(sesion);
        }

        if (dto.getEjercicioId() != null) {
            Ejercicio ejercicio = new Ejercicio();
            ejercicio.setId(dto.getEjercicioId());
            entity.setEjercicio(ejercicio);
        }

        return entity;
    }

    // ActualizarEjercicioSesionDTO → Entidad
    public EjercicioSesion toEntity(ActualizarEjercicioSesionDTO dto) {
        if (dto == null) {
            return null;
        }

        EjercicioSesion entity = new EjercicioSesion();
        entity.setDuracion(dto.getDuracion());
        entity.setCaloriasQuemadas(dto.getCaloriasQuemadas());
        entity.setSeries(dto.getSeries());
        entity.setRepeticiones(dto.getRepeticiones());
        entity.setPesoUtilizado(dto.getPesoUtilizado());
        entity.setRir(dto.getRir());

        // Relación con otras entidades
        if (dto.getSesionId() != null) {
            SesionEntrenamiento sesion = new SesionEntrenamiento();
            sesion.setId(dto.getSesionId());
            entity.setSesion(sesion);
        }

        if (dto.getEjercicioId() != null) {
            Ejercicio ejercicio = new Ejercicio();
            ejercicio.setId(dto.getEjercicioId());
            entity.setEjercicio(ejercicio);
        }

        return entity;
    }

    // Entidad → DTO
    public EjercicioSesionDTO toDTO(EjercicioSesion entity) {
        if (entity == null) {
            return null;
        }

        EjercicioSesionDTO dto = new EjercicioSesionDTO();
        dto.setId(entity.getId());
        dto.setDuracion(entity.getDuracion());
        dto.setCaloriasQuemadas(entity.getCaloriasQuemadas());
        dto.setSeries(entity.getSeries());
        dto.setRepeticiones(entity.getRepeticiones());
        dto.setPesoUtilizado(entity.getPesoUtilizado());
        dto.setRir(entity.getRir());

        if (entity.getSesion() != null) {
            dto.setSesionId(entity.getSesion().getId());
        }

        if (entity.getEjercicio() != null) {
            dto.setEjercicioId(entity.getEjercicio().getId());
        }

        return dto;
    }

    // Lista de Entidades → Lista de DTOs
    public List<EjercicioSesionDTO> toDTOList(List<EjercicioSesion> entities) {
        if (entities == null) {
            return List.of();
        }

        return entities.stream()
            .map(this::toDTO)
            .toList();
    }

    // Lista de DTOs → Lista de Entidades
    public List<EjercicioSesion> toEntityList(List<CrearEjercicioSesionDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }

        return dtos.stream()
            .map(this::toEntity)
            .toList();
    }
}