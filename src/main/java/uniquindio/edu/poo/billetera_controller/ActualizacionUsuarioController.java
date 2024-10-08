package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
<<<<<<< HEAD
import uniquindio.edu.poo.billetera_model.Sesion;
=======
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
import uniquindio.edu.poo.billetera_model.Usuario;

public class ActualizacionUsuarioController {

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
    private TextField ContraseñaField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;
<<<<<<< HEAD
=======
    private boolean esAdmin;
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c

    private Usuario usuarioEncontrado;

    public ActualizacionUsuarioController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        identificacionField.setPromptText("Identificación");
        nombreField.setPromptText("Nombre Completo");
        correoField.setPromptText("Correo");
        telefonoField.setPromptText("Teléfono");
        direccionField.setPromptText("Dirección");
        ContraseñaField.setPromptText("Contraseña");

        TextField[] fields = { identificacionField, nombreField, correoField, telefonoField, direccionField,
                ContraseñaField };

        for (TextField field : fields) {
            field.setOnMouseClicked(event -> limpiarCampoTexto(field));
            field.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue && field.getText().isEmpty()) {
                    field.setPromptText(field.getPromptText());
                }
            });
        }
    }

    private void limpiarCampoTexto(TextField campoTexto) {
        campoTexto.clear();
    }

<<<<<<< HEAD
=======
    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
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
        nombreField.setText(usuario.getNombre());
        correoField.setText(usuario.getCorreo());
        telefonoField.setText(usuario.getTelefono());
        direccionField.setText(usuario.getDireccion());
        ContraseñaField.setText(usuario.getContraseña());
    }

    @FXML
    private void ActualizarUsuario() throws IOException {
        if (usuarioEncontrado == null) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Primero debe buscar un usuario.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        usuarioEncontrado.setNombre(nombreField.getText());
        usuarioEncontrado.setCorreo(correoField.getText());
        usuarioEncontrado.setTelefono(telefonoField.getText());
        usuarioEncontrado.setDireccion(direccionField.getText());
        usuarioEncontrado.setContraseña(ContraseñaField.getText());

        try {
            billeteraVirtual.getUsuarioCRUD().actualizar(usuarioEncontrado);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Usuario actualizado exitosamente.");
            mensajeLabel.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al actualizar el usuario.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void Volver() throws IOException {
<<<<<<< HEAD
        if (Sesion.getEsAdmin()) {
=======
        if (esAdmin) {
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
            App.setRoot("Administrador", "Administrador");
        } else {
            App.setRoot("Usuario", "Usuario");
        }
    }
}
