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

    public Cliente crearCliente(Cliente cliente, Long planId) {
        Plan plan = planService.resolverPlan(planId);
        cliente.setPlan(plan);

        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente cliente, Long planId) throws EntityNotFoundException {
        Cliente clienteExistente = obtenerClientePorId(id);
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setDocumento(cliente.getDocumento());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setNroDireccion(cliente.getNroDireccion());
        clienteExistente.setObraSocial(cliente.getObraSocial());
        clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteExistente.setPlan(cliente.getPlan());

        return clienteRepository.save(clienteExistente);
    }
    
    public void eliminarCliente(Long id) throws EntityNotFoundException {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }

    public Cliente resolverPlan(Long clienteId) throws IllegalArgumentException, EntityNotFoundException {
        if (clienteId == null) {
            throw new IllegalArgumentException("El ID del plan no puede ser nulo");
        }

        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("El plan con ID " + clienteId + " no existe"));
    }
}