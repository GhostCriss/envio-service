package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.dto.EnvioRequestDTO;
import cl.duoc.backend_api.dto.EnvioResponseDTO;
import cl.duoc.backend_api.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService service;

    @PostMapping("/generar")
    public ResponseEntity<EnvioResponseDTO> registrarDespacho(@Valid @RequestBody EnvioRequestDTO request) {
        EnvioResponseDTO respuesta = service.generarEnvio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}