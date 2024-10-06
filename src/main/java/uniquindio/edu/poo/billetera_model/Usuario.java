package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
<<<<<<< HEAD
public class Usuario extends Persona {

    public double saldoTotal;
    private Billetera_virtual billeteraVirtual;

    public Usuario(String id, String contraseña, String nombre, String correo, String telefono, String direccion,
            double saldoTotal) {
        super(id, contraseña, nombre, correo, telefono, direccion);
        this.saldoTotal = saldoTotal;
=======

public class Usuario extends Persona {

    public double saldoTotal;
    public String contraseña;

    public Usuario(String id, String contraseña, String nombre, String correo, String telefono, String direccion,
            double saldoTotal) {
        super(id, nombre, correo, telefono, direccion);

        this.saldoTotal = saldoTotal;
        this.contraseña = contraseña;

>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
    }

}
