package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;

public class EliminacionUsuarioController {

    @FXML
    private TextField identificacionField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    public EliminacionUsuarioController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        identificacionField.setPromptText("Identificación");

        identificacionField.setOnMouseClicked(event -> limpiarCampoTexto(event, identificacionField));

        identificacionField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && identificacionField.getText().isEmpty()) {
                identificacionField.setPromptText("Identificación");
            }
        });
    }

    private void limpiarCampoTexto(MouseEvent event, TextField campoTexto) {
        campoTexto.clear();
    }

    @FXML
    private void EliminarUsuario() throws IOException {
        String identificacion = identificacionField.getText();

        if (identificacion.isEmpty() || identificacion == null) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese una identificación.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        try {
            billeteraVirtual.getUsuarioCRUD().eliminar(identificacion);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Usuario eliminado exitosamente.");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("El usuario no está registrado.");
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionUsuarios", "Gestión Usuarios");
    }

}
