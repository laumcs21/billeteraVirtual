package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import java.text.DecimalFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Usuario;

public class LecturaUsuarioController {

    @FXML
    private TextField identificacionField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField correoField;

    @FXML
    private TextField telefonoField;

    @FXML
    private TextField direccionField;

    @FXML
    private TextField saldoActualField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    private Usuario usuarioEncontrado;

    public LecturaUsuarioController() {
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
    private void BuscarUsuario() throws IOException {
        String identificacion = identificacionField.getText();

        if (identificacion.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese una identificación.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            usuarioEncontrado = billeteraVirtual.getUsuarioCRUD().leer(identificacion);
            if (usuarioEncontrado != null) {
                llenarCamposConUsuario(usuarioEncontrado);
                mensajeLabel.setVisible(false);
            } else {
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("El usuario no está registrado.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al buscar el usuario.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void llenarCamposConUsuario(Usuario usuario) {
        DecimalFormat formatoDecimal = new DecimalFormat("#");
        nombreField.setText(usuario.getNombre());
        correoField.setText(usuario.getCorreo());
        telefonoField.setText(usuario.getTelefono());
        direccionField.setText(usuario.getDireccion());
        saldoActualField.setText(formatoDecimal.format(usuario.getSaldoTotal()));
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionUsuarios");
    }

}
