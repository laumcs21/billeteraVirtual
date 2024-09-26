package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import uniquindio.edu.poo.billetera_app.App;

public class GestionUsuariosController {

    @FXML
    private void CrearUsuario() throws IOException {
        App.setRoot("CreacionUsuario");
    }

    @FXML
    private void ActualizarUsuario() throws IOException {
        App.setRoot("ActualizacionUsuario");
    }

    @FXML
    private void EliminarUsuario() throws IOException {
        App.setRoot("EliminacionUsuario");
    }

    @FXML
    private void BuscarUsuario() throws IOException {
        App.setRoot("LecturaUsuario");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Administrador");
    }
}
