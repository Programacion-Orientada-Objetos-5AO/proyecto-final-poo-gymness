package ar.edu.huergo.jsanchezortega.gymness.service.rutina;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import ar.edu.huergo.jsanchezortega.gymness.entity.persona.Cliente;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.*;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.OdjetivoRutinaRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.rutina.RutinaRepository;
import ar.edu.huergo.jsanchezortega.gymness.service.persona.ClienteService;
import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RutinaServiceTest {

    @Mock
    private RutinaRepository rutinaRepository;
    @Mock
    private OdjetivoRutinaRepository odjetivoRutinaRepository;
    @Mock
    private SesionEntrenamientoService sesionEntrenamientoService;
    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private RutinaService rutinaService;

    private Rutina rutina;
    private OdjetivoRutina objetivo;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objetivo = new OdjetivoRutina(1L, "Fuerza");
        cliente = new Cliente();
        cliente.setId(5L);
        rutina = new Rutina(1L, "Rutina fuerza", "Descripción", null, objetivo, List.of(), cliente);
    }

    @Test
    @DisplayName("Debería obtener todas las rutinas")
    void testObtenerTodasLasRutinas() {
        when(rutinaRepository.findAll()).thenReturn(List.of(rutina));
        List<Rutina> resultado = rutinaService.obtenerTodasLasRutinas();
        assertEquals(1, resultado.size());
        verify(rutinaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debería lanzar excepción si no se encuentra la rutina por ID")
    void testObtenerRutinaPorId_NoExiste() {
        when(rutinaRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> rutinaService.obtenerRutinaPorId(1L));
    }

    @Test
    @DisplayName("Debería crear una rutina correctamente")
    void testCrearRutina() {
        when(odjetivoRutinaRepository.findById(1L)).thenReturn(Optional.of(objetivo));
        when(clienteService.resolverCliente(5L)).thenReturn(cliente);
        when(sesionEntrenamientoService.resolveSesionEntrenamientos(any())).thenReturn(List.of());
        when(rutinaRepository.save(any())).thenReturn(rutina);

        Rutina nueva = rutinaService.crearRutina(rutina, 1L, List.of(), 5L);
        assertNotNull(nueva);
        assertEquals("Rutina fuerza", nueva.getNombre());
    }

    @Test
    @DisplayName("Debería eliminar una rutina existente")
    void testEliminarRutina() {
        when(rutinaRepository.findById(1L)).thenReturn(Optional.of(rutina));
        rutinaService.eliminarRutina(1L);
        verify(rutinaRepository, times(1)).delete(rutina);
    }
}
