package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.billetera_model.BuscarUsuarioPorID;

public class UsuarioController {

    private String idUsuario = Sesion.getIdUsuario();

    @FXML
    private TextArea saldoField;

    @FXML
    public void initialize() {

        Usuario usuario = BuscarUsuarioPorID.buscarUsuarioPorIdentificacion(idUsuario);
        double saldo = usuario.getSaldoTotal();

        saldoField.setText(String.format("%.2f", saldo));
    }

    @FXML
    private void Actualizar() throws IOException {
        App.setRoot("ActualizacionUsuario", "Actualización Usuario");
    }

    @FXML
    private void Transacciones() throws IOException {
        App.setRoot("GestionTransacciones", "Gestión transacciones");
    }

    @FXML
    private void Cuentas() throws IOException {
        App.setRoot("BusquedaCuentasUsuario", "Busqueda Cuentas Usuario");
    }

    @FXML
    private void Presupuesto() throws IOException {
        App.setRoot("ActualizacionUsuario", "Actualización Usuario");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("InicioSesion", "Inicio Sesion");
    }
}
