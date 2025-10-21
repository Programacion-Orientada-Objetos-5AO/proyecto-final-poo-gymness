package ar.edu.huergo.jsanchezortega.gymness.mapper.rutina;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.CrearRutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.OdjetivoRutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.RutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.OdjetivoRutina;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Rutina;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.OdjetivoRutinaRepository;

@Component
public class RutinaMapper {

    @Autowired
    private OdjetivoRutinaRepository odjetivoRutinaRepository;

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

     public Rutina toEntity(CrearRutinaDTO dto, Cliente cliente) {
        if (dto == null) {
            return null;
        }
        Rutina rutina = new Rutina();
        rutina.setNombre(dto.getNombre());
        rutina.setDescripcion(dto.getDescripcion());
        rutina.setFechaCreacion(LocalDateTime.now());
        rutina.setCliente(cliente);

        if (dto.getObjetivoId() != null) {
            OdjetivoRutina objetivo = odjetivoRutinaRepository.findById(dto.getObjetivoId())
                .orElseThrow(() -> new RuntimeException("Objetivo no encontrado"));
            rutina.setOdjetivo(objetivo);
        }

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
            dto.setSesionEntrenamientoIds(entity.getSesiones().stream()
                .map(sesion -> sesion.getId())
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
