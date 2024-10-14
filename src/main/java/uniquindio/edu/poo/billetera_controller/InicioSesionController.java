package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;
import uniquindio.edu.poo.billetera_model.Administrador;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.billetera_model.Usuario;

public class InicioSesionController {

    @FXML
    private TextField identificacionField;

    @FXML
    private TextField contraseñaField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

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
            Sesion.setEsAdmin(true);
            App.setRoot("Administrador", "Administrador");
            ArchivoUtil.guardarRegistroLog(" Inicio de Sesión Administrador --> ID: " + admin.getId() + " - Nombre: " + admin.getNombre() + " - Correo: " + admin.getCorreo() + " - Telefono: " + admin.getTelefono() + " - Dirección: " + admin.getDireccion(), 
                1, "btnIniciarSesion", "C:\\td\\persistencia\\log\\logInicioSesion.txt");
        } else {
            try {
                Usuario usuario = billeteraVirtual.getUsuarioCRUD().leer(identificacion);
                if (usuario.getContraseña().equals(contraseña)) {
                    Sesion.setEsAdmin(false);
                    Sesion.setIdUsuario(usuario.getId());
                    App.setRoot("Usuario", "Usuario");
                    ArchivoUtil.guardarRegistroLog(" Inicio de Sesión Usuario --> ID: " + usuario.getId() + " - Nombre: " + usuario.getNombre() + " - Correo: " + usuario.getCorreo() + " - Telefono: " + usuario.getTelefono() + " - Dirección: " + usuario.getDireccion(),1, "btnIniciarSesion", "C:\\td\\persistencia\\log\\logInicioSesion.txt");
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