package ar.edu.huergo.jsanchezortega.gymness.mapper.persona;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.CrearClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setDocumento(dto.getDocumento());
        cliente.setDireccion(dto.getDireccion());
        cliente.setNroDireccion(dto.getNroDireccion());
        cliente.setObraSocial(dto.getObraSocial());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());
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
        dto.setDocumento(entity.getDocumento());
        dto.setDireccion(entity.getDireccion());
        dto.setNroDireccion(entity.getNroDireccion());
        dto.setObraSocial(entity.getObraSocial());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        
        if (entity.getPlanes() != null) {
            dto.setPlanIds(entity.getPlanes().stream()
                    .map(Plan::getId)
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