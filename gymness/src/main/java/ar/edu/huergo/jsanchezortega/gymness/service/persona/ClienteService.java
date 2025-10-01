package ar.edu.huergo.jsanchezortega.gymness.service.persona;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;
import ar.edu.huergo.jsanchezortega.gymness.repository.persona.ClienteRepository;
import ar.edu.huergo.jsanchezortega.gymness.service.plan.PlanService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PlanService planService;

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) throws EntityNotFoundException {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
    }

    public Cliente crearCliente(Cliente cliente, List<Long> plaId) {
        List<Plan> plans = planService.resolverPlan(plaId);
        
        if (!plans.isEmpty()) {
            cliente.setPlanes(plans);
        }
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente cliente) throws EntityNotFoundException {
        Cliente clienteExistente = obtenerClientePorId(id);
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setDocumento(cliente.getDocumento());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setNroDireccion(cliente.getNroDireccion());
        clienteExistente.setObraSocial(cliente.getObraSocial());
        clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento());
        return clienteRepository.save(clienteExistente);
    }
    
    public void eliminarCliente(Long id) throws EntityNotFoundException {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }
}