package ar.edu.huergo.jsanchezortega.gymness.repository.persona;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
}
