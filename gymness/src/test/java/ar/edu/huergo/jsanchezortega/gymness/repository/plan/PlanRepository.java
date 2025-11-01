package ar.edu.huergo.jsanchezortega.gymness.repository.plan;

import ar.edu.huergo.jsanchezortega.gymness.entity.plan.Plan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("PlanRepository Test")
class PlanRepositoryTest {

    @Autowired
    private PlanRepository planRepository;

    private Plan plan1;
    private Plan plan2;
    private Plan plan3;

    @BeforeEach
    void setUp() {
        planRepository.deleteAll();

        plan1 = new Plan();
        plan1.setNombre("Plan Básico");
        plan1.setDescripcion("Acceso al gimnasio 3 veces por semana");
        plan1.setPrecio(15000.0);

        plan2 = new Plan();
        plan2.setNombre("Plan Premium");
        plan2.setDescripcion("Acceso ilimitado y clases grupales");
        plan2.setPrecio(30000.0);

        plan3 = new Plan();
        plan3.setNombre("Plan Intermedio");
        plan3.setDescripcion("Acceso al gimnasio 5 veces por semana");
        plan3.setPrecio(22000.0);

        planRepository.saveAll(List.of(plan1, plan2, plan3));
    }

    @Test
    @DisplayName("Debe encontrar un plan por nombre")
    void debeEncontrarPlanPorNombre() {
        Optional<Plan> resultado = planRepository.findByNombre("Plan Básico");

        assertTrue(resultado.isPresent());
        assertEquals("Plan Básico", resultado.get().getNombre());
    }

    @Test
    @DisplayName("Debe verificar existencia de plan por nombre")
    void debeVerificarExistenciaPorNombre() {
        assertTrue(planRepository.existsByNombre("Plan Premium"));
        assertFalse(planRepository.existsByNombre("Plan inexistente"));
    }

    @Test
    @DisplayName("Debe encontrar planes por rango de precios")
    void debeEncontrarPlanesPorRangoDePrecios() {
        List<Plan> resultados = planRepository.findByPrecioBetween(20000.0, 32000.0);

        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().anyMatch(p -> p.getNombre().equals("Plan Premium")));
        assertTrue(resultados.stream().anyMatch(p -> p.getNombre().equals("Plan Intermedio")));
    }

    @Test
    @DisplayName("Debe encontrar planes con precio menor que el indicado")
    void debeEncontrarPlanesConPrecioMenor() {
        List<Plan> resultados = planRepository.findByPrecioLessThan(20000.0);

        assertEquals(1, resultados.size());
        assertEquals("Plan Básico", resultados.get(0).getNombre());
    }

    @Test
    @DisplayName("Debe encontrar planes con precio mayor que el indicado")
    void debeEncontrarPlanesConPrecioMayor() {
        List<Plan> resultados = planRepository.findByPrecioGreaterThan(20000.0);

        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().allMatch(p -> p.getPrecio() > 20000.0));
    }

    @Test
    @DisplayName("Debe encontrar planes cuya descripción contenga un texto")
    void debeEncontrarPlanesPorDescripcion() {
        List<Plan> resultados = planRepository.findByDescripcionContaining("clases");

        assertEquals(1, resultados.size());
        assertEquals("Plan Premium", resultados.get(0).getNombre());
    }

    @Test
    @DisplayName("Debe encontrar planes cuyo nombre contenga un texto")
    void debeEncontrarPlanesPorNombreParcial() {
        List<Plan> resultados = planRepository.findByNombreContaining("Plan");

        assertEquals(3, resultados.size());
    }

    @Test
    @DisplayName("Debe guardar un nuevo plan correctamente")
    void debeGuardarNuevoPlan() {
        Plan nuevo = new Plan();
        nuevo.setNombre("Plan Avanzado");
        nuevo.setDescripcion("Acceso total + entrenador personal");
        nuevo.setPrecio(45000.0);

        Plan guardado = planRepository.save(nuevo);

        assertNotNull(guardado.getId());
        assertEquals("Plan Avanzado", guardado.getNombre());
        assertTrue(planRepository.existsByNombre("Plan Avanzado"));
    }
}
