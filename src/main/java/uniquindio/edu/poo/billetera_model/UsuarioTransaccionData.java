package uniquindio.edu.poo.billetera_model;

public class UsuarioTransaccionData {
    private final String nombreUsuario;
    private final int cantidadTransacciones;

    public UsuarioTransaccionData(String nombreUsuario, int cantidadTransacciones) {
        this.nombreUsuario = nombreUsuario;
        this.cantidadTransacciones = cantidadTransacciones;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getCantidadTransacciones() {
        return cantidadTransacciones;
    }
}
