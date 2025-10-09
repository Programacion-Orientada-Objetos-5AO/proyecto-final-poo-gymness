package ar.edu.huergo.jsanchezortega.gymness.mapper.rutina;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.EjercicioSesionDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.EjercicioSesion;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.SesionEntrenamiento;

public class EjercicioSesionMapper {

    // DTO → Entidad
    public static EjercicioSesion toEntity(EjercicioSesionDTO dto) {
        if (dto == null) {
            return null;
        }

        EjercicioSesion entity = new EjercicioSesion();
        entity.setId(dto.getId());
        entity.setDuracion(dto.getDuracion());
        entity.setCaloriasQuemadas(dto.getCaloriasQuemadas());
        entity.setSeries(dto.getSeries());
        entity.setRepeticiones(dto.getRepeticiones());
        entity.setPesoUtilizado(dto.getPesoUtilizado());
        entity.setRir(dto.getRir());

        // Relación con otras entidades
        SesionEntrenamiento sesion = new SesionEntrenamiento();
        sesion.setId(dto.getSesionId());
        entity.setSesion(sesion);

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(dto.getEjercicioId());
        entity.setEjercicio(ejercicio);

        return entity;
    }

    // Entidad → DTO
    public static EjercicioSesionDTO toDTO(EjercicioSesion entity) {
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
}
