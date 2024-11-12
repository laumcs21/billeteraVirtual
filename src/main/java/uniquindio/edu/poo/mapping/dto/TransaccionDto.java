package uniquindio.edu.poo.mapping.dto;

import java.io.Serializable;
import java.time.LocalDate;

import uniquindio.edu.poo.billetera_model.TipoTransaccion;

public record TransaccionDto(String idUsuario, String id, LocalDate fecha, TipoTransaccion tipo, double monto,
                String descripcion, String numeroCuentaOrigen, String numeroCuentaDestino, String idCategoria)
                implements Serializable {
}
