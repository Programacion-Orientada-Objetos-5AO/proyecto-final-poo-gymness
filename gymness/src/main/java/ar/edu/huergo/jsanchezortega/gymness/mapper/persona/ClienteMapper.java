package ar.edu.huergo.jsanchezortega.gymness.mapper.persona;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ActualizarClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.CrearClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.plan.PlanDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;


@Component
public class ClienteMapper {

    

    public Cliente toEntity(ActualizarClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setDocumento(clienteDTO.getDocumento() != null ? clienteDTO.getDocumento().toString() : null);
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setNroDireccion(clienteDTO.getNroDireccion());
        cliente.setObraSocial(clienteDTO.getObraSocial());
        cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        return cliente;
    }

    public Cliente toEntity(CrearClienteDTO dto, Plan plan) {
        if (dto == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setDocumento(dto.getDocumento());
        cliente.setDireccion(dto.getDireccion());
        cliente.setNroDireccion(dto.getNroDireccion());
        cliente.setObraSocial(dto.getObraSocial());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());
        cliente.setPlan(plan); // asigno el plan único

        return cliente;
    }

    public ClienteDTO toDTO(Cliente entity) {
        if (entity == null) {
            return null;
        }

        ClienteDTO dto = new ClienteDTO();

        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setDocumento(entity.getDocumento() != null ? Integer.valueOf(entity.getDocumento()) : null);
        dto.setDireccion(entity.getDireccion());
        dto.setNroDireccion(entity.getNroDireccion());
        dto.setObraSocial(entity.getObraSocial());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setPlanId(entity.getPlan() != null ? entity.getPlan().getId() : null);

        if (entity.getPlan() != null) {
            dto.setPlanId(entity.getPlan().getId());
            PlanDTO planDTO = new PlanDTO();
            planDTO.setId(entity.getPlan().getId());
            planDTO.setNombre(entity.getPlan().getNombre());
            dto.setPlanId(entity.getPlan().getId());
        }

        if (entity.getRutinas() != null) {
            dto.setRutinasIds(entity.getRutinas().stream()
                .map(rutina -> rutina.getId())
                .collect(Collectors.toList()));
        }
        return dto;
    }

    public List<ClienteDTO> toDTOList(List<Cliente> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}