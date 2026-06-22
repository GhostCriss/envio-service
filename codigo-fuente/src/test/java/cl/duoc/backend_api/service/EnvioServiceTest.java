package cl.duoc.backend_api.service;

import cl.duoc.backend_api.dto.EnvioRequestDTO;
import cl.duoc.backend_api.dto.EnvioResponseDTO;
import cl.duoc.backend_api.model.Envio;
import cl.duoc.backend_api.repository.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnvioServiceTest {

    @Mock
    private EnvioRepository repository;

    @InjectMocks
    private EnvioService service;

    @Test
    void debeGenerarEnvioConCodigoYEstadoPendiente() {
        EnvioRequestDTO request = new EnvioRequestDTO();
        request.setPedidoId(100L);
        request.setDireccionDestino("Santiago Centro 101");

        Envio envioGuardado = new Envio();
        envioGuardado.setId(1L);
        envioGuardado.setPedidoId(100L);
        envioGuardado.setDireccionDestino("Santiago Centro 101");
        envioGuardado.setCodigoSeguimiento("TRK-TEST-1234");
        envioGuardado.setEstado("PENDIENTE");

        when(repository.save(any(Envio.class))).thenReturn(envioGuardado);

        EnvioResponseDTO response = service.generarEnvio(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("PENDIENTE", response.getEstado());
        assertEquals("TRK-TEST-1234", response.getCodigoSeguimiento());
    }
}