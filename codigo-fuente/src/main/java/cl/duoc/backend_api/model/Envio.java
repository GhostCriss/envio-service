package cl.duoc.backend_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pedido_id", nullable = false, unique = true)
    private Long pedidoId; // Clave lógica de negocio compartida

    @Column(nullable = false)
    private String direccionDestino;

    @Column(nullable = false)
    private String codigoSeguimiento;

    @Column(nullable = false)
    private String estado; // PENDIENTE, EN_TRANSITO, ENTREGADO
}