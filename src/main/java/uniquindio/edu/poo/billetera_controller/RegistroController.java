package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_exception.CorreoElectronicoException;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Usuario;

public class RegistroController {

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
    private TextField saldoInicialField;

    @FXML
    private TextField contraseñaField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    public RegistroController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        identificacionField.setPromptText("Identificación");
        nombreField.setPromptText("Nombre Completo");
        correoField.setPromptText("Correo");
        telefonoField.setPromptText("Telefono");
        direccionField.setPromptText("Dirección");
        saldoInicialField.setPromptText("Saldo inicial");
        contraseñaField.setPromptText("Contraseña");

        TextField[] fields = { identificacionField, nombreField, correoField, telefonoField,
                direccionField, saldoInicialField, contraseñaField };

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

    @FXML
    private void Registrarse() throws IOException {
        String identificacion = identificacionField.getText();
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String telefono = telefonoField.getText();
        String direccion = direccionField.getText();
        String saldoInicialText = saldoInicialField.getText();
        String contraseña = contraseñaField.getText();

        if (identificacion.isEmpty() || nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()
                || direccion.isEmpty() || saldoInicialText.isEmpty() || contraseña.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, complete todos los campos.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            double saldoInicial = Double.parseDouble(saldoInicialText);

            Usuario usuario = new Usuario(identificacion, contraseña, nombre, correo, telefono, direccion,
                    saldoInicial);

            billeteraVirtual.getUsuarioCRUD().crear(usuario);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Usuario registrado exitosamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Saldo inicial debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("El usuario ya está registrado.");
        } catch (CorreoElectronicoException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("El correo electrónico no es válido");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al crear el usuario");
        }
    }

    private void limpiarCampos() {
        identificacionField.clear();
        nombreField.clear();
        correoField.clear();
        telefonoField.clear();
        direccionField.clear();
        saldoInicialField.clear();
        contraseñaField.clear();

        identificacionField.setPromptText("Identificación");
        nombreField.setPromptText("Nombre Completo");
        correoField.setPromptText("Correo");
        telefonoField.setPromptText("Telefono");
        direccionField.setPromptText("Dirección");
        saldoInicialField.setPromptText("Saldo inicial");
        contraseñaField.setPromptText("Contraseña");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("InicioSesion", "Inicio Sesion");
    }
}