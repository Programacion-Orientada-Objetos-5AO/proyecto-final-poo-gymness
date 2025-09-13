package ar.edu.huergo.jsanchezortega.gymness.entity.ejercicio;

import org.junit.jupiter.api.Test;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.TipoEjercicio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TipoEjercicio Entity")
class TipoEjercicioTest {

    private TipoEjercicio tipoEjercicio;

    @BeforeEach
    void setUp() {
        tipoEjercicio = new TipoEjercicio();
    }

    @Test
    @DisplayName("crear TipoEjercicio con constructor por defecto")
    void debeCrearTipoEjercicioConConstructorPorDefecto() {
        assertNotNull(tipoEjercicio);
        assertNull(tipoEjercicio.getId());
        assertNull(tipoEjercicio.getNombre());
    }

    @Test
    @DisplayName("crear TipoEjercicio con constructor con nombre")
    void debeCrearTipoEjercicioConConstructorConNombre() {
        String nombre = "Fuerza";
        TipoEjercicio tipo = new TipoEjercicio(nombre);
        
        assertNotNull(tipo);
        assertEquals(nombre, tipo.getNombre());
        assertNull(tipo.getId());
    }

    @Test
    @DisplayName("crear TipoEjercicio con todos los parámetros")
    void debeCrearTipoEjercicioConTodosLosParametros() {
        Long id = 1L;
        String nombre = "Cardio";
        TipoEjercicio tipo = new TipoEjercicio(id, nombre);
        
        assertNotNull(tipo);
        assertEquals(id, tipo.getId());
        assertEquals(nombre, tipo.getNombre());
    }

    @Test
    @DisplayName("establecer y obtener ID correctamente")
    void debeEstablecerYObtenerIdCorrectamente() {
        Long expectedId = 1L;
        tipoEjercicio.setId(expectedId);
        assertEquals(expectedId, tipoEjercicio.getId());
    }

    @Test
    @DisplayName("establecer y obtener nombre correctamente")
    void debeEstablecerYObtenerNombreCorrectamente() {
        String expectedNombre = "HIIT";
        tipoEjercicio.setNombre(expectedNombre);
        assertEquals(expectedNombre, tipoEjercicio.getNombre());
    }

    @Test
    @DisplayName("verificar equals y hashCode")
    void debeVerificarEqualsYHashCode() {
        TipoEjercicio tipo1 = new TipoEjercicio(1L, "Fuerza");
        TipoEjercicio tipo2 = new TipoEjercicio(1L, "Fuerza");
        TipoEjercicio tipo3 = new TipoEjercicio(2L, "Cardio");
        
        assertEquals(tipo1, tipo2);
        assertEquals(tipo1.hashCode(), tipo2.hashCode());
        assertNotEquals(tipo1, tipo3);
    }
}
