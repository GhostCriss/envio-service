package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.dto.EnvioRequestDTO;
import cl.duoc.backend_api.dto.EnvioResponseDTO;
import cl.duoc.backend_api.service.EnvioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EnvioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EnvioService service;

    @InjectMocks
    private EnvioController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void debeRegistrarDespachoYRetornar201() throws Exception {
        EnvioResponseDTO responseDTO = new EnvioResponseDTO(1L, "TRK-001", "PENDIENTE");
        when(service.generarEnvio(any(EnvioRequestDTO.class))).thenReturn(responseDTO);

        String jsonBody = "{\"pedidoId\": 10, \"direccionDestino\": \"Macul 890\"}";

        mockMvc.perform(post("/api/envios/generar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isCreated());
    }

    @Test
    void debeRetornar404SiRutaNoExiste() throws Exception {
        mockMvc.perform(get("/api/envios/ruta-falsa"))
                .andExpect(status().isNotFound());
    }

    @Test
    void debeRetornar405SiMetodoHttpEsInvalido() throws Exception {
        // La ruta /generar espera un POST, no un GET
        mockMvc.perform(get("/api/envios/generar"))
                .andExpect(status().isMethodNotAllowed());
    }
}