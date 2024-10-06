package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cuenta {

<<<<<<< HEAD
    private String idUsuario;
=======
    private Usuario usuario;
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
    private String id;
    private String nombreBanco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;

<<<<<<< HEAD
    public Cuenta(String idUsuario, String id, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        this.idUsuario = idUsuario;
=======
    public Cuenta(Usuario usuario, String id, String nombreBanco, String numeroCuenta, TipoCuenta tipoCuenta) {
        this.usuario = usuario;
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
        this.id = id;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
    }

}
