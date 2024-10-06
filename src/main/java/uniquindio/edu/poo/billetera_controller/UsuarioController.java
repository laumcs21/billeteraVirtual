package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import uniquindio.edu.poo.billetera_app.App;

public class UsuarioController {

    @FXML
    private void Actualizar() throws IOException {
        App.setRoot("ActualizacionUsuario", "Actualización Usuario");
    }

    @FXML
    private void Transacciones() throws IOException {
        App.setRoot("ActualizacionUsuario", "Actualización Usuario");
    }

    @FXML
    private void Cuentas() throws IOException {
<<<<<<< HEAD
        App.setRoot("BusquedaCuentasUsuario", "Busqueda Cuentas Usuario");
=======
        App.setRoot("ActualizacionUsuario", "Actualización Usuario");
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
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
