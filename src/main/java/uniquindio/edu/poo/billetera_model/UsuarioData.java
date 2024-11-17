package uniquindio.edu.poo.billetera_model;

public class UsuarioData {

    private final String nombre;
    private final int cantidadTransacciones;

    public UsuarioData(String nombre, int cantidadTransacciones) {
        this.nombre = nombre;
        this.cantidadTransacciones = cantidadTransacciones;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadTransacciones() {
        return cantidadTransacciones;
    }
}

