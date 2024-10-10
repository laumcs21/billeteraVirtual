package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import uniquindio.edu.poo.billetera_app.App;

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
        App.setRoot("GestionTransacciones", "Gestión Transacciones");
    }

    @FXML
    private void GestionarCategorias() throws IOException {
        App.setRoot("GestionCategorias", "Gestión Categorias");
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
