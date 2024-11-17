package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_exception.FormatoNumericoIncorrectoException;
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

        if (identificacion.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese una identificación.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {

            if (!identificacion.matches("\\d+")) {
                throw new FormatoNumericoIncorrectoException("El ID de usuario debe contener solo números.");
            }

            billeteraVirtual.getUsuarioCRUD().eliminar(identificacion);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Usuario eliminado exitosamente.");
            mensajeLabel.setStyle("-fx-text-fill: green;");

        } catch (FormatoNumericoIncorrectoException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText(e.getMessage());
            mensajeLabel.setStyle("-fx-text-fill: red;");
        } catch (IllegalArgumentException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("El usuario no está registrado.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al eliminar el usuario.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionUsuarios", "Gestión Usuarios");
    }
}
