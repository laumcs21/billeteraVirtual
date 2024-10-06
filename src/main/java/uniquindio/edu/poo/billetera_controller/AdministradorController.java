package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import uniquindio.edu.poo.billetera_app.App;
<<<<<<< HEAD
import uniquindio.edu.poo.billetera_model.Sesion;
=======
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c

public class AdministradorController {

    @FXML
    private void GestionarUsuarios() throws IOException {
        App.setRoot("GestionUsuarios", "Gestión Usuarios");
    }

    @FXML
<<<<<<< HEAD
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
=======
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
    private void Volver() throws IOException {
        App.setRoot("InicioSesion", "Inicio Sesion");
    }

}
