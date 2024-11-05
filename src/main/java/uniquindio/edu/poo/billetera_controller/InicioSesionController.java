package uniquindio.edu.poo.billetera_controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
    private void IniciarSesion() {
        String identificacion = identificacionField.getText().trim();
        String contraseña = contraseñaField.getText().trim();

        if (identificacion.isEmpty() || contraseña.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, complete todos los campos.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try (Socket socket = new Socket("localhost", 9091);
                DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
                DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream())) {

            // Enviar las credenciales al servidor
            flujoSalida.writeUTF(identificacion);
            flujoSalida.writeUTF(contraseña);

            // Leer la respuesta del servidor
            String respuesta = flujoEntrada.readUTF();

            switch (respuesta) {
                case "ACCESO_CONCEDIDO_ADMINISTRADOR":
                    Sesion.setEsAdmin(true);
                    Sesion.setIdUsuario(identificacion);
                    App.setRoot("Administrador", "Administrador");
                    ArchivoUtil.guardarRegistroLog("Inicio de Sesión Administrador --> ID: " + identificacion, 1,
                            "btnIniciarSesion", "C:\\td\\persistencia\\log\\logInicioSesion.txt");
                    break;

                case "ACCESO_CONCEDIDO_USUARIO":
                    Sesion.setEsAdmin(false);
                    Sesion.setIdUsuario(identificacion);
                    App.setRoot("Usuario", "Usuario");
                    ArchivoUtil.guardarRegistroLog("Inicio de Sesión Usuario --> ID: " + identificacion, 1,
                            "btnIniciarSesion", "C:\\td\\persistencia\\log\\logInicioSesion.txt");
                    break;

                case "ACCESO_DENEGADO":
                    mensajeLabel.setVisible(true);
                    mensajeLabel.setText("Credenciales incorrectas.");
                    mensajeLabel.setStyle("-fx-text-fill: red;");
                    break;

                case "USUARIO_NO_REGISTRADO":
                    mensajeLabel.setVisible(true);
                    mensajeLabel.setText("El usuario no está registrado.");
                    mensajeLabel.setStyle("-fx-text-fill: red;");
                    break;

                default:
                    mensajeLabel.setVisible(true);
                    mensajeLabel.setText("Respuesta desconocida del servidor.");
                    mensajeLabel.setStyle("-fx-text-fill: red;");
                    break;
            }

        } catch (IOException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error de conexión con el servidor.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    private void Registrarse() throws IOException {
        App.setRoot("Registro", "Registro");
    }
}