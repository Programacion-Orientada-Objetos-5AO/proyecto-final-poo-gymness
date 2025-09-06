package ar.edu.huergo.jsanchezortega.gymness.service.persona;


import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;

public class ClienteMapper {

    // DTO -> Entidad
    public Cliente toEntity(ClienteDTO dto){
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

    // Entidad -> DTO
    public ClienteDTO toDTO(Cliente entity){
        if (entity == null) {
            return null;
        }

        return new ClienteDTO(
            entity.getId();
            entity.getNombre();
            entity.getApellido();
            entity.getDocumento();
            entity.getDireccion();
            entity.getNroDireccion();
            entity.getFechaNacimiento();
        );
    }
}


