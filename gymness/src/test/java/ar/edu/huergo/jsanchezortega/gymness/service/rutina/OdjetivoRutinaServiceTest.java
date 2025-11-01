package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.OdjetivoRutina;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.OdjetivoRutinaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OdjetivoRutinaService Tests")
class OdjetivoRutinaServiceTest {

    @Mock
    private OdjetivoRutinaRepository repository;

    @InjectMocks
    private OdjetivoRutinaService service;

    private OdjetivoRutina odjetivo;

    @BeforeEach
    void setUp() {
        odjetivo = new OdjetivoRutina(1L, "BAJAR_PESO");
    }

    @Test
    @DisplayName("obtener todos los objetivos")
    void debeObtenerTodosLosObjetivos() {
        List<OdjetivoRutina> lista = Arrays.asList(
                new OdjetivoRutina(1L, "BAJAR_PESO"),
                new OdjetivoRutina(2L, "GANAR_MUSCULO")
        );
        when(repository.findAll()).thenReturn(lista);

        List<OdjetivoRutina> result = service.obtenerTodoLosOdjetivoRutinas();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("obtener objetivo por ID existente")
    void debeObtenerPorIdExistente() {
        when(repository.findById(1L)).thenReturn(Optional.of(odjetivo));

        OdjetivoRutina result = service.obteneOdjetivoRutinaPorId(1L);

        assertNotNull(result);
        assertEquals("BAJAR_PESO", result.getNombre());
    }

    @Test
    @DisplayName("lanzar excepción si no encuentra ID")
    void debeLanzarExcepcionSiNoEncuentraId() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.obteneOdjetivoRutinaPorId(999L));
    }

    @Test
    @DisplayName("crear objetivo correctamente")
    void debeCrearObjetivo() {
        OdjetivoRutina nuevo = new OdjetivoRutina("GANAR_MUSCULO");
        OdjetivoRutina guardado = new OdjetivoRutina(2L, "GANAR_MUSCULO");
        when(repository.save(nuevo)).thenReturn(guardado);

        OdjetivoRutina result = service.crearOdjetivoRutina(nuevo);

        assertEquals(2L, result.getId());
        verify(repository, times(1)).save(nuevo);
    }

    @Test
    @DisplayName("actualizar objetivo existente")
    void debeActualizarObjetivo() {
        when(repository.findById(1L)).thenReturn(Optional.of(odjetivo));
        when(repository.save(any(OdjetivoRutina.class))).thenReturn(odjetivo);

        OdjetivoRutina actualizado = new OdjetivoRutina("MANTENIMIENTO");
        OdjetivoRutina result = service.actualizarOdjetivoRutina(1L, actualizado);

        assertEquals("MANTENIMIENTO", odjetivo.getNombre());
        verify(repository).save(odjetivo);
    }

    @Test
    @DisplayName("eliminar objetivo existente")
    void debeEliminarObjetivo() {
        when(repository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(() -> service.eliminarOdjetivoRutina(1L));
        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("lanzar excepción al eliminar objetivo inexistente")
    void debeLanzarExcepcionAlEliminarInexistente() {
        when(repository.existsById(99L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> service.eliminarOdjetivoRutina(99L));
    }
}
