package ar.edu.huergo.jsanchezortega.gymness.mapper.rutina;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearEjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.EjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.MusculoOdjetivoDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.TipoEjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;

@Component
public class EjercicioMapper {

    public Ejercicio toEntity(EjercicioDTO dto) {
        if (dto == null) {
            return null;
        }
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(dto.getId());
        ejercicio.setNombre(dto.getNombre());
        ejercicio.setDescripcion(dto.getDescripcion());
        ejercicio.setInstrucciones(dto.getInstrucciones());
        ejercicio.setVideoUrl(dto.getVideoUrl());
        ejercicio.setImagenUrl(dto.getImagenUrl());
        ejercicio.setActivo(dto.getActivo());
        return ejercicio;
    }

    public Ejercicio toEntity(CrearEjercicioDTO dto) {
        if (dto == null) {
            return null;
        }
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre(dto.getNombre());
        ejercicio.setDescripcion(dto.getDescripcion());
        ejercicio.setInstrucciones(dto.getInstrucciones());
        ejercicio.setVideoUrl(dto.getVideoUrl());
        ejercicio.setImagenUrl(dto.getImagenUrl());
        ejercicio.setActivo(dto.getActivo());
        return ejercicio;
    }

    public EjercicioDTO toDTO(Ejercicio entity) {
        if (entity == null) {
            return null;
        }

        EjercicioDTO dto = new EjercicioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setInstrucciones(entity.getInstrucciones());
        dto.setVideoUrl(entity.getVideoUrl());
        dto.setImagenUrl(entity.getImagenUrl());
        dto.setActivo(entity.getActivo());
        
        if (entity.getMusculoObjetivo() != null) {
            dto.setMusculoObjetivoId(entity.getMusculoObjetivo().getId());
            MusculoOdjetivoDTO musculoDTO = new MusculoOdjetivoDTO();
            musculoDTO.setId(entity.getMusculoObjetivo().getId());
            musculoDTO.setNombre(entity.getMusculoObjetivo().getNombre());
            dto.setMusculoObjetivo(musculoDTO);
        }
        
        if (entity.getTipoEjercicio() != null) {
            dto.setTipoEjercicioId(entity.getTipoEjercicio().getId());
            TipoEjercicioDTO tipoDTO = new TipoEjercicioDTO();
            tipoDTO.setId(entity.getTipoEjercicio().getId());
            tipoDTO.setNombre(entity.getTipoEjercicio().getNombre());
            dto.setTipoEjercicio(tipoDTO);
        }
        
        return dto;
    }

    public List<EjercicioDTO> toDTOList(List<Ejercicio> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}