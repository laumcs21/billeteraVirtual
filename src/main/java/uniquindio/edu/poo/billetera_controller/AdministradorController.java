package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Sesion;

public class AdministradorController {

    @FXML
    private void GestionarUsuarios() throws IOException {
        App.setRoot("GestionUsuarios", "Gestión Usuarios");
    }

    @FXML
    private void GestionarCuentas() throws IOException {
        App.setRoot("GestionCuentas", "Gestión Cuentas");
    }

    @FXML
    private void GestionarTransacciones() throws IOException {
        App.setRoot("GestionCuentas", "Gestión Cuentas");
    }

    @FXML
    private void Estadisticas() throws IOException {
        App.setRoot("GestionCuentas", "Gestión Cuentas");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("InicioSesion", "Inicio Sesion");
    }

}
