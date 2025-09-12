package ar.edu.huergo.jsanchezortega.gymness.service.ejercicio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.TipoEjercicio;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.TipoEjercicioRepository;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.TipoEjercicioService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TipoEjercicioService Tests")
class TipoEjercicioServiceTest {

    @Mock
    private TipoEjercicioRepository tipoEjercicioRepository;

    @InjectMocks
    private TipoEjercicioService tipoEjercicioService;

    private TipoEjercicio tipoEjercicio;

    @BeforeEach
    void setUp() {
        tipoEjercicio = new TipoEjercicio(1L, "Fuerza");
    }

    @Test
    @DisplayName("obtener todos los tipos de ejercicio")
    void debeObtenerTodosLosTiposDeEjercicio() {
        List<TipoEjercicio> tiposEsperados = Arrays.asList(
            new TipoEjercicio(1L, "Fuerza"),
            new TipoEjercicio(2L, "Cardio")
        );
        when(tipoEjercicioRepository.findAll()).thenReturn(tiposEsperados);

        List<TipoEjercicio> resultado = tipoEjercicioService.obtenerTodoTipoEjercicio();

        assertEquals(2, resultado.size());
        assertEquals("Fuerza", resultado.get(0).getNombre());
        verify(tipoEjercicioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("obtener tipo de ejercicio por ID")
    void debeObtenerTipoEjercicioPorId() {
        when(tipoEjercicioRepository.findById(1L)).thenReturn(Optional.of(tipoEjercicio));

        TipoEjercicio resultado = tipoEjercicioService.obtenerTipoEjercicioPorId(1L);

        assertNotNull(resultado);
        assertEquals("Fuerza", resultado.getNombre());
        verify(tipoEjercicioRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("lanzar excepción cuando no encuentra tipo por ID")
    void debeLanzarExcepcionCuandoNoEncuentraTipoPorId() {
        when(tipoEjercicioRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            tipoEjercicioService.obtenerTipoEjercicioPorId(999L);
        });
    }

    @Test
    @DisplayName("crear tipo de ejercicio")
    void debeCrearTipoDeEjercicio() {
        TipoEjercicio nuevoTipo = new TipoEjercicio("Cardio");
        TipoEjercicio tipoGuardado = new TipoEjercicio(2L, "Cardio");
        
        when(tipoEjercicioRepository.save(nuevoTipo)).thenReturn(tipoGuardado);

        TipoEjercicio resultado = tipoEjercicioService.crearTipoEjercicio(nuevoTipo);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getId());
        assertEquals("Cardio", resultado.getNombre());
        verify(tipoEjercicioRepository, times(1)).save(nuevoTipo);
    }

    @Test
    @DisplayName("actualizar tipo de ejercicio")
    void debeActualizarTipoDeEjercicio() {
        TipoEjercicio tipoActualizado = new TipoEjercicio("HIIT");
        
        when(tipoEjercicioRepository.findById(1L)).thenReturn(Optional.of(tipoEjercicio));
        when(tipoEjercicioRepository.save(any(TipoEjercicio.class))).thenReturn(tipoEjercicio); 

        TipoEjercicio resultado = tipoEjercicioService.actualizarTipoEjercicio(1L, tipoActualizado);

        assertEquals("HIIT", tipoEjercicio.getNombre());
        verify(tipoEjercicioRepository, times(1)).findById(1L);
        verify(tipoEjercicioRepository, times(1)).save(tipoEjercicio);
    }

    @Test
    @DisplayName("obtener tipo de ejercicio por nombre")
    void debeObtenerTipoEjercicioPorNombre() {
        when(tipoEjercicioRepository.findByNombre("Fuerza")).thenReturn(Optional.of(tipoEjercicio));

        Optional<TipoEjercicio> resultado = tipoEjercicioService.obtenerTipoEjercicioPorNombre("Fuerza");

        assertTrue(resultado.isPresent());
        assertEquals("Fuerza", resultado.get().getNombre());
    }

    @Test
    @DisplayName("eliminar tipo de ejercicio")
    void debeEliminarTipoDeEjercicio() {
        when(tipoEjercicioRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> tipoEjercicioService.eliminarTipoEjercicio(1L));

        verify(tipoEjercicioRepository, times(1)).existsById(1L);
        verify(tipoEjercicioRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("lanzar excepción al eliminar tipo inexistente")
    void debeLanzarExcepcionAlEliminarTipoInexistente() {
        when(tipoEjercicioRepository.existsById(999L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            tipoEjercicioService.eliminarTipoEjercicio(999L);
        });
    }

    @Test
    @DisplayName("resolver tipos de ejercicio por IDs")
    void debeResolverTiposDeEjercicioPorIds() {
        List<Long> ids = Arrays.asList(1L, 2L);
        List<TipoEjercicio> tipos = Arrays.asList(
            new TipoEjercicio(1L, "Fuerza"),
            new TipoEjercicio(2L, "Cardio")
        );
        
        when(tipoEjercicioRepository.findAllById(ids)).thenReturn(tipos);

        List<TipoEjercicio> resultado = tipoEjercicioService.resolverTipo(ids);

        assertEquals(2, resultado.size());
        verify(tipoEjercicioRepository, times(1)).findAllById(ids);
    }

    @Test
    @DisplayName("lanzar excepción con lista de IDs vacía")
    void debeLanzarExcepcionConListaDeIdsVacia() {
        assertThrows(IllegalArgumentException.class, () -> {
            tipoEjercicioService.resolverTipo(Arrays.asList());
        });
    }
}