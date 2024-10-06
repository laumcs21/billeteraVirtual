package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario extends Persona {

    public double saldoTotal;
    private Billetera_virtual billeteraVirtual;

    public Usuario(String id, String contraseña, String nombre, String correo, String telefono, String direccion,
            double saldoTotal) {
        super(id, contraseña, nombre, correo, telefono, direccion);
        this.saldoTotal = saldoTotal;
    }

}
