
package ar.edu.huergo.jsanchezortega.gymness.controller.persona;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.huergo.jsanchezortega.gymness.dto.persona.ClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.dto.persona.CrearClienteDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import ar.edu.huergo.jsanchezortega.gymness.mapper.persona.ClienteMapper;
import ar.edu.huergo.jsanchezortega.gymness.service.persona.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {
    
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        List<ClienteDTO> clienteDTOs = clienteMapper.toDTOList(clientes);
        return ResponseEntity.ok(clienteDTOs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENTE') and @clienteService.esClientePropio(authentication.name, #id))")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        ClienteDTO clienteDTO = clienteMapper.toDTO(cliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody CrearClienteDTO crearClienteDTO) {
        Cliente cliente = clienteMapper.toEntity(crearClienteDTO);
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        ClienteDTO clienteDTO = clienteMapper.toDTO(nuevoCliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENTE') and @clienteService.esClientePropio(authentication.name, #id))")
    public ResponseEntity<ClienteDTO> actualizarCliente(
            @PathVariable Long id, 
            @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteActualizado = clienteService.actualizarCliente(id, cliente);
        ClienteDTO clienteActualizadoDTO = clienteMapper.toDTO(clienteActualizado);
        return ResponseEntity.ok(clienteActualizadoDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}