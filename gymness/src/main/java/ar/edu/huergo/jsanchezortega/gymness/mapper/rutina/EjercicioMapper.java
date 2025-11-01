package ar.edu.huergo.jsanchezortega.gymness.mapper.rutina;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearEjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.EjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.MusculoOdjetivoDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.TipoEjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Ejercicio;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.MusculoObjetivo;

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

        // Tipo de ejercicio (ManyToOne → uno solo)
        if (entity.getTipoEjercicio() != null) {
            dto.setTipoEjercicioIds(entity.getTipoEjercicio().getId());
            TipoEjercicioDTO tipoDTO = new TipoEjercicioDTO();
            tipoDTO.setId(entity.getTipoEjercicio().getId());
            tipoDTO.setNombre(entity.getTipoEjercicio().getNombre());
            dto.setTipoEjercicio(tipoDTO);
        }

        // Músculos objetivo (ManyToMany → lista)
        if (entity.getMusculosObjetivo() != null && !entity.getMusculosObjetivo().isEmpty()) {
            List<MusculoOdjetivoDTO> musculosDTO = new ArrayList<>();
            List<Long> ids = new ArrayList<>();

            for (MusculoObjetivo musculo : entity.getMusculosObjetivo()) {
                ids.add(musculo.getId());

                MusculoOdjetivoDTO musculoDTO = new MusculoOdjetivoDTO();
                musculoDTO.setId(musculo.getId());
                musculoDTO.setNombre(musculo.getNombre());
                musculosDTO.add(musculoDTO);
            }

            dto.setMusculoObjetivoIds(ids);
            dto.setMusculoObjetivo(musculosDTO);
        }

        return dto;
    }


    public List<EjercicioDTO> toDTOList(List<Ejercicio> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}