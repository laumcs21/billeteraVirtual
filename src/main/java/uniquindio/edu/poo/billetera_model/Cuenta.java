package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cuenta {

    private Usuario usuario;
    private String id;
    private String nombreBanco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;

    public Cuenta(Usuario usuario, String id, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        this.usuario = usuario;
        this.id = id;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
    }

}
