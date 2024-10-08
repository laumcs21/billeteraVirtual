package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cuenta {

    private String idUsuario;
    private String id;
    private String nombreBanco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private double saldo;

    public Cuenta(String idUsuario, String id, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta,
            double saldo) {
        this.idUsuario = idUsuario;
        this.id = id;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
    }

}
