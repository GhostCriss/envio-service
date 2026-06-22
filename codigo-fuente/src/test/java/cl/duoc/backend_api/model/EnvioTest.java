package cl.duoc.backend_api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnvioTest {

    @Test
    void debeAsignarYRecuperarValoresBasicos() {
        Envio envio = new Envio();
        envio.setId(1L);
        envio.setPedidoId(500L);
        envio.setDireccionDestino("Calle Falsa 123");
        envio.setCodigoSeguimiento("TRK-12345");
        envio.setEstado("PENDIENTE");

        assertEquals(1L, envio.getId());
        assertEquals(500L, envio.getPedidoId());
        assertEquals("Calle Falsa 123", envio.getDireccionDestino());
        assertEquals("TRK-12345", envio.getCodigoSeguimiento());
        assertEquals("PENDIENTE", envio.getEstado());
    }

    @Test
    void debeCrearInstanciaConConstructorCompleto() {
        Envio envio = new Envio(2L, 600L, "Avenida Siempreviva", "TRK-9876", "EN_TRANSITO");
        
        assertNotNull(envio);
        assertEquals(600L, envio.getPedidoId());
        assertEquals("EN_TRANSITO", envio.getEstado());
    }

    @Test
    void debeInicializarNuloConConstructorVacio() {
        Envio envio = new Envio();
        assertNull(envio.getId());
        assertNull(envio.getCodigoSeguimiento());
    }
}