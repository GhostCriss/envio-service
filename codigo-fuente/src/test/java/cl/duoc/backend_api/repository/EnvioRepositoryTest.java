package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.Envio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EnvioRepositoryTest {

    @Autowired
    private EnvioRepository repository;

    @Test
    void debeGuardarUnEnvioExitosamente() {
        Envio envio = new Envio();
        envio.setPedidoId(10L);
        envio.setDireccionDestino("Providencia 456");
        envio.setCodigoSeguimiento("TRK-PROV-01");
        envio.setEstado("PENDIENTE");

        Envio guardado = repository.save(envio);
        
        assertNotNull(guardado.getId());
        assertEquals("TRK-PROV-01", guardado.getCodigoSeguimiento());
    }

    @Test
    void debeEncontrarEnvioPorId() {
        Envio envio = new Envio();
        envio.setPedidoId(20L);
        envio.setDireccionDestino("Las Condes 789");
        envio.setCodigoSeguimiento("TRK-LC-02");
        envio.setEstado("ENTREGADO");
        Envio guardado = repository.save(envio);

        Optional<Envio> encontrado = repository.findById(guardado.getId());
        
        assertTrue(encontrado.isPresent());
        assertEquals("ENTREGADO", encontrado.get().getEstado());
    }

    @Test
    void debeRetornarVacioSiNoExiste() {
        Optional<Envio> encontrado = repository.findById(999L);
        assertFalse(encontrado.isPresent());
    }
}