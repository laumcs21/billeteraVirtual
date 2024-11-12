package uniquindio.edu.poo.mapping.dto;

import java.io.Serializable;

public record PresupuestoDto(String idUsuario, String id, String nombre, double monto, String idCategoria)
                implements Serializable {
}
