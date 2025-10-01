package ar.edu.huergo.jsanchezortega.gymness.mapper.persona;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ActualizarClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.CrearClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;
import io.jsonwebtoken.lang.Collections;
import jakarta.validation.Valid;

@Component
public class ClienteMapper {

    

    public Cliente toEntity(ClienteDTO clienteDTO) {
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

    public Cliente toEntity(CrearClienteDTO dto) {
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
        
        if (dto.getPlanIds() != null && !dto.getPlanIds().isEmpty()) {
            List<Plan> planes = dto.getPlanIds().stream()
                .map(planId -> {
                    Plan plan = new Plan();
                    plan.setId(planId);
                    return plan;
                })
                .collect(Collectors.toList());
            cliente.setPlanes(planes);
        }
        
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

        // Planes (ManyToMany)
        if (entity.getPlanes() != null && !entity.getPlanes().isEmpty()) {
            List<Long> planIds = entity.getPlanes()
                                    .stream()
                                    .map(Plan::getId)
                                    .toList();
            dto.setPlanIds(planIds);
        } else {
            dto.setPlanIds(Collections.emptyList());
        }

        return dto;
    }

    public List<ClienteDTO> toDTOList(List<Cliente> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}