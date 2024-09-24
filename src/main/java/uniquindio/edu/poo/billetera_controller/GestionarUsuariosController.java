package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import uniquindio.edu.poo.billetera_app.App;

public class GestionarUsuariosController {

    @FXML
    private void CrearUsuario() throws IOException {
        App.setRoot("CrearUsuario");
    }

    @FXML
    private void ActualizarUsuario() throws IOException {
        App.setRoot("ActualizarUsuario");
    }

    @FXML
    private void EliminarUsuario() throws IOException {
        App.setRoot("EliminarUsuario");
    }

    @FXML
    private void BuscarUsuario() throws IOException {
        App.setRoot("BuscarUsuario");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Administrador");
    }
}
