package ar.edu.huergo.jsanchezortega.gymness.controller.ejercicio;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.huergo.jsanchezortega.gymness.controller.rutina.TipoEjercicioController;
import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.TipoEjercicioDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.TipoEjercicio;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.TipoEjercicioService;
import ar.edu.huergo.jsanchezortega.gymness.service.security.JwtTokenService;

@WebMvcTest(TipoEjercicioController.class)
class TipoEjercicioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoEjercicioService tipoEjercicioService;

    @MockBean
    private JwtTokenService jwtTokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "ADMIN")
    void obtenerTodosLosTiposEjercicio_DebeRetornarLista() throws Exception {
        List<TipoEjercicio> tipos = Arrays.asList(
                createTipoEjercicio(1L, "Cardio"),
                createTipoEjercicio(2L, "Fuerza")
        );
        when(tipoEjercicioService.obtenerTodoTipoEjercicio()).thenReturn(tipos);

        mockMvc.perform(get("/api/tipos-ejercicio").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Cardio")));
    }

    @Test
    void obtenerTodosLosTiposEjercicio_SinAuth_Retorna401() throws Exception {
        mockMvc.perform(get("/api/tipos-ejercicio"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void obtenerPorId_ConIdExistente_RetornaTipo() throws Exception {
        TipoEjercicio tipo = createTipoEjercicio(1L, "Cardio");
        when(tipoEjercicioService.obtenerTipoEjercicioPorId(1L)).thenReturn(tipo);

        mockMvc.perform(get("/api/tipos-ejercicio/1").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Cardio")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void buscarPorNombre_Existente_RetornaTipo() throws Exception {
        TipoEjercicio tipo = createTipoEjercicio(1L, "Cardio");
        when(tipoEjercicioService.obtenerTipoEjercicioPorNombre("Cardio"))
                .thenReturn(Optional.of(tipo));

        mockMvc.perform(get("/api/tipos-ejercicio/buscar").param("nombre", "Cardio").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Cardio")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void buscarPorNombre_Inexistente_Retorna404() throws Exception {
        when(tipoEjercicioService.obtenerTipoEjercicioPorNombre("NoExiste"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/tipos-ejercicio/buscar").param("nombre", "NoExiste").with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void crear_ConDatosValidos_CreaYRetorna() throws Exception {
        TipoEjercicioDTO dto = new TipoEjercicioDTO(null, "Nuevo");
        TipoEjercicio creado = createTipoEjercicio(1L, "Nuevo");
        when(tipoEjercicioService.crearTipoEjercicio(any())).thenReturn(creado);

        mockMvc.perform(post("/api/tipos-ejercicio")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Nuevo")));
    }

    @Test
    @WithMockUser(roles = "CLIENTE")
    void crear_ComCliente_Retorna403() throws Exception {
        TipoEjercicioDTO dto = new TipoEjercicioDTO(null, "Nuevo");

        mockMvc.perform(post("/api/tipos-ejercicio")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void actualizar_ConDatosValidos_ActualizaYRetorna() throws Exception {
        TipoEjercicioDTO dto = new TipoEjercicioDTO(1L, "Actualizado");
        TipoEjercicio actualizado = createTipoEjercicio(1L, "Actualizado");
        when(tipoEjercicioService.actualizarTipoEjercicio(anyLong(), any())).thenReturn(actualizado);

        mockMvc.perform(put("/api/tipos-ejercicio/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Actualizado")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void eliminar_ConIdValido_Retorna204() throws Exception {
        doNothing().when(tipoEjercicioService).eliminarTipoEjercicio(1L);

        mockMvc.perform(delete("/api/tipos-ejercicio/1").with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminar_SinAuth_Retorna401() throws Exception {
        mockMvc.perform(delete("/api/tipos-ejercicio/1"))
                .andExpect(status().isUnauthorized());
    }

    private TipoEjercicio createTipoEjercicio(Long id, String nombre) {
        TipoEjercicio tipo = new TipoEjercicio(nombre);
        try {
            var field = TipoEjercicio.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(tipo, id);
        } catch (Exception e) {
        }
        return tipo;
    }
}