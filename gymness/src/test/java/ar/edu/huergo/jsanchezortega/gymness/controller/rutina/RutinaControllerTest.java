package ar.edu.huergo.jsanchezortega.gymness.controller.rutina;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import ar.edu.huergo.jsanchezortega.gymness.dto.rutina.RutinaDTO;
import ar.edu.huergo.jsanchezortega.gymness.entity.rutina.Rutina;
import ar.edu.huergo.jsanchezortega.gymness.mapper.rutina.RutinaMapper;
import ar.edu.huergo.jsanchezortega.gymness.service.rutina.RutinaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RutinaController.class)
class RutinaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RutinaService rutinaService;
    @MockBean
    private RutinaMapper rutinaMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/rutinas debería devolver lista de rutinas")
    void testObtenerTodas() throws Exception {
        Rutina rutina = new Rutina();
        rutina.setId(1L);
        rutina.setNombre("Rutina Test");
        rutina.setDescripcion("Descripcion test");

        RutinaDTO dto = new RutinaDTO();
        dto.setId(1L);
        dto.setNombre("Rutina Test");

        when(rutinaService.obtenerTodasLasRutinas()).thenReturn(List.of(rutina));
        when(rutinaMapper.toDTOList(any())).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/rutinas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Rutina Test"));
    }
}
