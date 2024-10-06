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

    public Cuenta(String idUsuario, String id, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        this.idUsuario = idUsuario;
        this.id = id;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
    }

}
