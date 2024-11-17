package uniquindio.edu.poo.billetera_model;


public class UsuarioSaldoData {
    private String nombreUsuario;
    private double saldo;
    private double promedio;

    public UsuarioSaldoData(String nombreUsuario, double saldo, double promedio) {
        this.nombreUsuario = nombreUsuario;
        this.saldo = saldo;
        this.promedio = promedio;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getPromedio() {
        return promedio;
    }
}
