package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Administrador;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
<<<<<<< HEAD
import uniquindio.edu.poo.billetera_model.Sesion;
=======
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
import uniquindio.edu.poo.billetera_model.Usuario;

public class InicioSesionController {

    @FXML
    private TextField identificacionField;

    @FXML
    private TextField contraseñaField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;
<<<<<<< HEAD
=======
    private boolean esAdmin;
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c

    public InicioSesionController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        identificacionField.setPromptText("Identificación");
        contraseñaField.setPromptText("Contraseña");

        identificacionField.setOnMouseClicked(event -> limpiarCampoTexto(event, identificacionField));

        contraseñaField.setOnMouseClicked(event -> limpiarCampoTexto(event, contraseñaField));

        identificacionField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && identificacionField.getText().isEmpty()) {
                identificacionField.setPromptText("Identificación");
            }
        });

        contraseñaField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && contraseñaField.getText().isEmpty()) {
                contraseñaField.setPromptText("Contraseña");
            }
        });
    }

    private void limpiarCampoTexto(MouseEvent event, TextField campoTexto) {
        campoTexto.clear();
    }

    @FXML
    private void IniciarSesion() throws IOException {

        String identificacion = identificacionField.getText().trim();
        String contraseña = contraseñaField.getText().trim();

        if (identificacion.isEmpty() || contraseña.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, complete todos los campos.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Administrador admin = Administrador.getInstance();
        if (admin.getId().equals(identificacion) && admin.getContraseña().equals(contraseña)) {
<<<<<<< HEAD
            Sesion.setEsAdmin(true);
            App.setRoot("Administrador", "Administrador");
=======
            App.setRoot("Administrador", "Administrador");
            esAdmin = true;
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
        } else {
            try {
                Usuario usuario = billeteraVirtual.getUsuarioCRUD().leer(identificacion);
                if (usuario.getContraseña().equals(contraseña)) {
<<<<<<< HEAD
                    Sesion.setEsAdmin(false);
                    Sesion.setIdUsuario(usuario.getId());
                    App.setRoot("Usuario", "Usuario");
=======
                    App.setRoot("Usuario", "Usuario");
                    esAdmin = false;
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
                } else {
                    mensajeLabel.setVisible(true);
                    mensajeLabel.setText("Credenciales incorrectas.");
                }
            } catch (IllegalArgumentException e) {
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("El usuario no está registrado.");
            }
        }
    }

    @FXML
    private void Registrarse() throws IOException {
        App.setRoot("Registro", "Registro");
    }
}