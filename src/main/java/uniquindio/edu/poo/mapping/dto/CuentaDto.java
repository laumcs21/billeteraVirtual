package uniquindio.edu.poo.mapping.dto;

import java.io.Serializable;

public record CuentaDto(String idUsuario, String id, String nombreBanco, String numeroCuenta, String tipoCuenta,
        double saldo) implements Serializable {
}
