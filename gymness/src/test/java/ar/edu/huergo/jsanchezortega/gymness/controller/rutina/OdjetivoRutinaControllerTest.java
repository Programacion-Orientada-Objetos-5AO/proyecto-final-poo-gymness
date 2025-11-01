package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.OdjetivoRutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.OdjetivoRutina;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.OdjetivoRutinaService;
import ar.edu.huergo.jsanchezortega.gymness.service.security.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OdjetivoRutinaController.class)
class OdjetivoRutinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OdjetivoRutinaService service;

    @MockBean
    private JwtTokenService jwtTokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "ADMIN")
    void obtenerTodosLosOdjetivos_DebeRetornarLista() throws Exception {
        List<OdjetivoRutina> lista = Arrays.asList(
                new OdjetivoRutina(1L, "BAJAR_PESO"),
                new OdjetivoRutina(2L, "GANAR_MUSCULO")
        );
        when(service.obtenerTodoLosOdjetivoRutinas()).thenReturn(lista);

        mockMvc.perform(get("/api/odjetivo-rutina").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("BAJAR_PESO")));
    }

    @Test
    void obtenerTodosLosOdjetivos_SinAuth_Retorna401() throws Exception {
        mockMvc.perform(get("/api/odjetivo-rutina"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void obtenerPorId_RetornaOdjetivo() throws Exception {
        OdjetivoRutina obj = new OdjetivoRutina(1L, "GANAR_MUSCULO");
        when(service.obteneOdjetivoRutinaPorId(1L)).thenReturn(obj);

        mockMvc.perform(get("/api/odjetivo-rutina/1").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("GANAR_MUSCULO")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void crear_ConDatosValidos_CreaYRetorna() throws Exception {
        OdjetivoRutinaDTO dto = new OdjetivoRutinaDTO(null, "NUEVO");
        OdjetivoRutina creado = new OdjetivoRutina(1L, "NUEVO");
        when(service.crearOdjetivoRutina(any())).thenReturn(creado);

        mockMvc.perform(post("/api/odjetivo-rutina")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("NUEVO")));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void actualizar_ConDatosValidos_RetornaActualizado() throws Exception {
        OdjetivoRutinaDTO dto = new OdjetivoRutinaDTO(1L, "ACTUALIZADO");
        OdjetivoRutina actualizado = new OdjetivoRutina(1L, "ACTUALIZADO");
        when(service.actualizarOdjetivoRutina(anyLong(), any())).thenReturn(actualizado);

        mockMvc.perform(put("/api/odjetivo-rutina/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("ACTUALIZADO")));
    }


}
