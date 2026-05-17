package cl.duoc.backend_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioResponseDTO {
    private Long envioId;
    private String codigoSeguimiento;
    private String estado;
}
