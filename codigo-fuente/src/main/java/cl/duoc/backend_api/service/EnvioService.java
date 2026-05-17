package cl.duoc.backend_api.service;

import cl.duoc.backend_api.dto.EnvioRequestDTO;
import cl.duoc.backend_api.dto.EnvioResponseDTO;
import cl.duoc.backend_api.model.Envio;
import cl.duoc.backend_api.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository repository;

    @Transactional
    public EnvioResponseDTO generarEnvio(EnvioRequestDTO request) {
        // Generación de un código de seguimiento simulado único por despacho
        String trackingCode = "TRK-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        
        Envio envio = new Envio();
        envio.setPedidoId(request.getPedidoId());
        envio.setDireccionDestino(request.getDireccionDestino());
        envio.setCodigoSeguimiento(trackingCode);
        envio.setEstado("PENDIENTE");

        Envio guardado = repository.save(envio);

        return new EnvioResponseDTO(guardado.getId(), guardado.getCodigoSeguimiento(), guardado.getEstado());
    }
}