package uniquindio.edu.poo.billetera_model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idUsuario;
    private String id;
    private String nombreBanco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private double saldo;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Cuenta(String idUsuario, String id, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta,
            double saldo) {
        this.idUsuario = idUsuario;
        this.id = id;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cuenta [idUsuario=" + idUsuario + ", id=" + id + ", nombreBanco=" + nombreBanco + ", numeroCuenta="
                + numeroCuenta + ", tipoCuenta=" + tipoCuenta + ", saldo=" + saldo + "]";
    }

}
