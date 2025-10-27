package ar.edu.huergo.jsanchezortega.gymness.repository.persona;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Especialidad;
import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Profesional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("ProfesionalRepository Test")
class ProfesionalRepositoryTest {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    private Especialidad especialidad1;
    private Especialidad especialidad2;

    private Profesional profesional1;
    private Profesional profesional2;
    private Profesional profesional3;

    @BeforeEach
    void setUp() {
        profesionalRepository.deleteAll();
        especialidadRepository.deleteAll();

        especialidad1 = new Especialidad();
        especialidad1.setNombre("Fisioterapia");
        especialidad2 = new Especialidad();
        especialidad2.setNombre("Nutrición");

        especialidad1 = especialidadRepository.save(especialidad1);
        especialidad2 = especialidadRepository.save(especialidad2);

        profesional1 = new Profesional();
        profesional1.setNombre("Laura");
        profesional1.setApellido("Gómez");
        profesional1.setEmail("laura@gmail.com");
        profesional1.setMatriculaProfesional("MAT123");
        profesional1.setActivo(true);
        profesional1.setEspecialidad(especialidad1);

        profesional2 = new Profesional();
        profesional2.setNombre("Carlos");
        profesional2.setApellido("Pérez");
        profesional2.setEmail("carlos@gmail.com");
        profesional2.setMatriculaProfesional("MAT456");
        profesional2.setActivo(false);
        profesional2.setEspecialidad(especialidad2);

        profesional3 = new Profesional();
        profesional3.setNombre("Sofía");
        profesional3.setApellido("López");
        profesional3.setEmail("sofia@gmail.com");
        profesional3.setMatriculaProfesional("MAT789");
        profesional3.setActivo(true);
        profesional3.setEspecialidad(especialidad2);

        profesionalRepository.saveAll(List.of(profesional1, profesional2, profesional3));
    }

    @Test
    @DisplayName("Debe encontrar profesional por email")
    void debeEncontrarPorEmail() {
        Optional<Profesional> resultado = profesionalRepository.findByEmail("laura@gmail.com");

        assertTrue(resultado.isPresent());
        assertEquals("Laura", resultado.get().getNombre());
    }

    @Test
    @DisplayName("Debe encontrar profesional por matrícula profesional")
    void debeEncontrarPorMatricula() {
        Optional<Profesional> resultado = profesionalRepository.findByMatriculaProfesional("MAT456");

        assertTrue(resultado.isPresent());
        assertEquals("Carlos", resultado.get().getNombre());
    }

    @Test
    @DisplayName("Debe verificar existencia por email")
    void debeVerificarExistenciaPorEmail() {
        assertTrue(profesionalRepository.existsByEmail("sofia@gmail.com"));
        assertFalse(profesionalRepository.existsByEmail("noexiste@gmail.com"));
    }

    @Test
    @DisplayName("Debe verificar existencia por matrícula profesional")
    void debeVerificarExistenciaPorMatricula() {
        assertTrue(profesionalRepository.existsByMatriculaProfesional("MAT123"));
        assertFalse(profesionalRepository.existsByMatriculaProfesional("MAT000"));
    }

    @Test
    @DisplayName("Debe encontrar profesionales por ID de especialidad")
    void debeEncontrarPorEspecialidadId() {
        List<Profesional> resultados = profesionalRepository.findByEspecialidadId(especialidad2.getId());

        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().allMatch(p -> p.getEspecialidad().getNombre().equals("Nutrición")));
    }

}
