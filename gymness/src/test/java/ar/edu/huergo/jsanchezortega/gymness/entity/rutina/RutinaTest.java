package ar.edu.huergo.jsanchezortega.gymness.entity.rutina;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RutinaTest {

    @Test
    @DisplayName("Debería crear una Rutina correctamente con valores iniciales")
    void testConstructorYGetters() {
        OdjetivoRutina objetivo = new OdjetivoRutina(1L, "Ganar músculo");
        Cliente cliente = new Cliente();
        cliente.setId(10L);

        Rutina rutina = new Rutina(1L, "Rutina de fuerza", "Entrenamiento completo", 
                LocalDateTime.now(), objetivo, List.of(), cliente);

        assertEquals("Rutina de fuerza", rutina.getNombre());
        assertEquals("Entrenamiento completo", rutina.getDescripcion());
        assertNotNull(rutina.getFechaCreacion());
        assertEquals(objetivo, rutina.getOdjetivo());
        assertEquals(cliente, rutina.getCliente());
    }

    @Test
    @DisplayName("Debería permitir modificar propiedades mediante setters")
    void testSetters() {
        Rutina rutina = new Rutina();
        rutina.setNombre("Rutina cardio");
        rutina.setDescripcion("Para bajar peso");

        assertEquals("Rutina cardio", rutina.getNombre());
        assertEquals("Para bajar peso", rutina.getDescripcion());
    }

    @Test
    @DisplayName("Equals y HashCode deberían funcionar correctamente por ID")
    void testEqualsAndHashCode() {
        Rutina r1 = new Rutina();
        r1.setId(1L);
        Rutina r2 = new Rutina();
        r2.setId(1L);

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}
    