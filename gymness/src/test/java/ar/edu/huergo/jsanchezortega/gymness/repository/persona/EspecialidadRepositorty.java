package ar.edu.huergo.jsanchezortega.gymness.repository.persona;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Especialidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("EspecialidadRepository Test")
class EspecialidadRepositoryTest {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    private Especialidad especialidad1;
    private Especialidad especialidad2;

    @BeforeEach
    void setUp() {
        especialidadRepository.deleteAll();

        especialidad1 = new Especialidad();
        especialidad1.setNombre("Nutrición Deportiva");

        especialidad2 = new Especialidad();
        especialidad2.setNombre("Fisioterapia");

        especialidadRepository.save(especialidad1);
        especialidadRepository.save(especialidad2);
    }

    @Test
    @DisplayName("Debe encontrar una especialidad por nombre")
    void debeEncontrarEspecialidadPorNombre() {
        Optional<Especialidad> resultado = especialidadRepository.findByNombre("Nutrición Deportiva");

        assertTrue(resultado.isPresent());
        assertEquals("Nutrición Deportiva", resultado.get().getNombre());
    }

    @Test
    @DisplayName("Debe devolver vacío si no existe una especialidad con ese nombre")
    void debeDevolverVacioSiNoExistePorNombre() {
        Optional<Especialidad> resultado = especialidadRepository.findByNombre("Entrenamiento Funcional");

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Debe verificar existencia de especialidad por nombre")
    void debeVerificarExistenciaPorNombre() {
        assertTrue(especialidadRepository.existsByNombre("Fisioterapia"));
        assertFalse(especialidadRepository.existsByNombre("Yoga"));
    }

    @Test
    @DisplayName("Debe guardar una nueva especialidad correctamente")
    void debeGuardarNuevaEspecialidad() {
        Especialidad nueva = new Especialidad();
        nueva.setNombre("Kinesiología");

        Especialidad guardada = especialidadRepository.save(nueva);

        assertNotNull(guardada.getId());
        assertEquals("Kinesiología", guardada.getNombre());
        assertTrue(especialidadRepository.existsByNombre("Kinesiología"));
    }
}
