package ar.edu.huergo.jsanchezortega.gymness.repository.persona;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("ClienteRepository Test")
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();

        cliente1 = new Cliente();
        cliente1.setNombre("Juan");
        cliente1.setApellido("Pérez");
        cliente1.setDocumento("12345678");
        cliente1.setDireccion("Av. Siempre Viva 742");
        cliente1.setObraSocial("OSDE");
        cliente1.setFechaNacimiento(LocalDate.of(1990, 5, 15));

        cliente2 = new Cliente();
        cliente2.setNombre("María");
        cliente2.setApellido("Gómez");
        cliente2.setDocumento("87654321");
        cliente2.setDireccion("Calle Falsa 123");
        cliente2.setObraSocial("Swiss Medical");
        cliente2.setFechaNacimiento(LocalDate.of(2000, 10, 1));

        cliente3 = new Cliente();
        cliente3.setNombre("Juana");
        cliente3.setApellido("López");
        cliente3.setDocumento("11223344");
        cliente3.setDireccion("Av. San Martín 100");
        cliente3.setObraSocial("OSDE");
        cliente3.setFechaNacimiento(LocalDate.of(1985, 2, 28));

        clienteRepository.saveAll(List.of(cliente1, cliente2, cliente3));
    }

    @Test
    @DisplayName("Debe encontrar un cliente por documento")
    void debeEncontrarClientePorDocumento() {
        Optional<Cliente> resultado = clienteRepository.findByDocumento("12345678");
        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getNombre());
    }

    @Test
    @DisplayName("Debe devolver true si existe cliente por documento")
    void debeVerificarExistenciaPorDocumento() {
        assertTrue(clienteRepository.existsByDocumento("87654321"));
        assertFalse(clienteRepository.existsByDocumento("00000000"));
    }

    @Test
    @DisplayName("Debe encontrar clientes cuyo nombre contenga texto")
    void debeEncontrarClientesPorNombreParcial() {
        List<Cliente> resultados = clienteRepository.findByNombreContaining("Juan");
        assertEquals(2, resultados.size());
    }

    @Test
    @DisplayName("Debe encontrar clientes cuyo apellido contenga texto")
    void debeEncontrarClientesPorApellidoParcial() {
        List<Cliente> resultados = clienteRepository.findByApellidoContaining("pé");
        assertFalse(resultados.isEmpty());
        assertEquals("Juan", resultados.get(0).getNombre());
    }

    @Test
    @DisplayName("Debe encontrar clientes por obra social")
    void debeEncontrarClientesPorObraSocial() {
        List<Cliente> resultados = clienteRepository.findByObraSocial("OSDE");
        assertEquals(2, resultados.size());
    }

    @Test
    @DisplayName("Debe encontrar clientes por dirección exacta")
    void debeEncontrarClientesPorDireccionExacta() {
        List<Cliente> resultados = clienteRepository.findByDireccion("Calle Falsa 123");
        assertEquals(1, resultados.size());
        assertEquals("María", resultados.get(0).getNombre());
    }

    @Test
    @DisplayName("Debe encontrar clientes nacidos entre dos fechas")
    void debeEncontrarClientesEntreFechas() {
        List<Cliente> resultados = clienteRepository.findByFechaNacimientoBetween(
                LocalDate.of(1988, 1, 1),
                LocalDate.of(2001, 1, 1)
        );
        assertEquals(2, resultados.size());
    }

    @Test
    @DisplayName("Debe encontrar clientes nacidos después de una fecha")
    void debeEncontrarClientesNacidosDespuesDeFecha() {
        List<Cliente> resultados = clienteRepository.findByFechaNacimientoAfter(LocalDate.of(1995, 1, 1));
        assertEquals(1, resultados.size());
        assertEquals("María", resultados.get(0).getNombre());
    }

    @Test
    @DisplayName("Debe encontrar clientes nacidos antes de una fecha")
    void debeEncontrarClientesNacidosAntesDeFecha() {
        List<Cliente> resultados = clienteRepository.findByFechaNacimientoBefore(LocalDate.of(1990, 1, 1));
        assertEquals(1, resultados.size());
        assertEquals("Juana", resultados.get(0).getNombre());
    }
}
