package ar.edu.huergo.jsanchezortega.gymness.entity.rutina;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OdjetivoRutina Entity")
class OdjetivoRutinaTest {

    private OdjetivoRutina odjetivo;

    @BeforeEach
    void setUp() {
        odjetivo = new OdjetivoRutina();
    }

    @Test
    @DisplayName("crear OdjetivoRutina con constructor por defecto")
    void debeCrearOdjetivoRutinaConConstructorPorDefecto() {
        assertNotNull(odjetivo);
        assertNull(odjetivo.getId());
        assertNull(odjetivo.getNombre());
    }

    @Test
    @DisplayName("crear OdjetivoRutina con constructor con nombre")
    void debeCrearOdjetivoRutinaConConstructorConNombre() {
        String nombre = "GANAR_MUSCULO";
        OdjetivoRutina obj = new OdjetivoRutina(nombre);

        assertNotNull(obj);
        assertEquals(nombre, obj.getNombre());
        assertNull(obj.getId());
    }

    @Test
    @DisplayName("crear OdjetivoRutina con todos los parámetros")
    void debeCrearOdjetivoRutinaConTodosLosParametros() {
        Long id = 1L;
        String nombre = "BAJAR_PESO";
        OdjetivoRutina obj = new OdjetivoRutina(id, nombre);

        assertEquals(id, obj.getId());
        assertEquals(nombre, obj.getNombre());
    }

    @Test
    @DisplayName("establecer y obtener ID correctamente")
    void debeEstablecerYObtenerIdCorrectamente() {
        Long expectedId = 10L;
        odjetivo.setId(expectedId);
        assertEquals(expectedId, odjetivo.getId());
    }

    @Test
    @DisplayName("establecer y obtener nombre correctamente")
    void debeEstablecerYObtenerNombreCorrectamente() {
        String expected = "MANTENIMIENTO";
        odjetivo.setNombre(expected);
        assertEquals(expected, odjetivo.getNombre());
    }

    @Test
    @DisplayName("equals y hashCode funcionan correctamente")
    void debeVerificarEqualsYHashCode() {
        OdjetivoRutina obj1 = new OdjetivoRutina(1L, "BAJAR_PESO");
        OdjetivoRutina obj2 = new OdjetivoRutina(1L, "BAJAR_PESO");
        OdjetivoRutina obj3 = new OdjetivoRutina(2L, "GANAR_MUSCULO");

        assertEquals(obj1, obj2);
        assertEquals(obj1.hashCode(), obj2.hashCode());
        assertNotEquals(obj1, obj3);
    }
}
