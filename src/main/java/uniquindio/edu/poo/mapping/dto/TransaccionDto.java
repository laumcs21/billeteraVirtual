package uniquindio.edu.poo.mapping.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record TransaccionDto(String idUsuario, String id, LocalDate fecha, String tipo, double monto,
        String descripcion, String numeroCuentaOrigen, String numeroCuentaDestino, String idCategoria)
        implements Serializable {
}
