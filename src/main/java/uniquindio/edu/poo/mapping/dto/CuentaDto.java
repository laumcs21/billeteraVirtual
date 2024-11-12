package uniquindio.edu.poo.mapping.dto;

import java.io.Serializable;

import uniquindio.edu.poo.billetera_model.TipoCuenta;

public record CuentaDto(String idUsuario, String id, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta,
                double saldo) implements Serializable {
}
