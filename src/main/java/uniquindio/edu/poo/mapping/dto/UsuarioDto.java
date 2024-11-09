package uniquindio.edu.poo.mapping.dto;

import java.io.Serializable;

public record UsuarioDto(String id, String nombre, String correo, String telefono, String direccion, double saldoTotal)
        implements Serializable {
}
