package ar.edu.huergo.jsanchezortega.gymness.mapper.persona;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.persona.CrearProfesionalDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.EspecialidadDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ProfesionalDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Profesional;

@Component
public class ProfesionalMapper {

    public Profesional toEntity(ProfesionalDTO dto) {
        if (dto == null) {
            return null;
        }
        return Profesional.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .documento(dto.getDocumento())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .matriculaProfesional(dto.getMatriculaProfesional())
                .activo(dto.getActivo())
                .build();
    }

    public Profesional toEntity(CrearProfesionalDTO dto) {
        if (dto == null) {
            return null;
        }
        return Profesional.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .documento(dto.getDocumento())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .matriculaProfesional(dto.getMatriculaProfesional())
                .activo(dto.getActivo())
                .build();
    }

    public ProfesionalDTO toDTO(Profesional entity) {
        if (entity == null) {
            return null;
        }

        ProfesionalDTO dto = new ProfesionalDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setDocumento(entity.getDocumento());
        dto.setEmail(entity.getEmail());
        dto.setTelefono(entity.getTelefono());
        dto.setMatriculaProfesional(entity.getMatriculaProfesional());
        dto.setActivo(entity.isActivo());
        
        if (entity.getEspecialidad() != null) {
            dto.setEspecialidadId(entity.getEspecialidad().getId());
            EspecialidadDTO especialidadDTO = new EspecialidadDTO();
            especialidadDTO.setId(entity.getEspecialidad().getId());
            especialidadDTO.setNombre(entity.getEspecialidad().getNombre());
            dto.setEspecialidad(especialidadDTO);
        }
        
        return dto;
    }

    public List<ProfesionalDTO> toDTOList(List<Profesional> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}