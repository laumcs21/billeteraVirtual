package uniquindio.edu.poo.billetera_model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    public double saldoTotal;
    private Billetera_virtual billeteraVirtual;


    public Usuario(String id, String contraseña, String nombre, String correo, String telefono, String direccion,
            double saldoTotal) {
        super(id, contraseña, nombre, correo, telefono, direccion);
        this.saldoTotal = saldoTotal;
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void actualizarSaldoTotal() {
        double nuevoSaldoTotal = billeteraVirtual.getCuentas().stream()
                .filter(cuenta -> cuenta.getIdUsuario().equals(this.getId()))
                .mapToDouble(Cuenta::getSaldo)
                .sum();

        this.saldoTotal = nuevoSaldoTotal;
    }

    @Override
    public String toString() {
        return "Usuario [saldoTotal=" + saldoTotal + ", billeteraVirtual=" + billeteraVirtual + "]";
    }

}
