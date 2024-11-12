package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_exception.ContrasenaException;
import uniquindio.edu.poo.billetera_exception.CorreoElectronicoException;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.billetera_model.UsuarioCRUD;
import uniquindio.edu.poo.mapping.dto.UsuarioDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class CreacionUsuarioController {

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

    private BancoMapper bancoMapper = BancoMapper.INSTANCE;

    public CreacionUsuarioController() {
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
        ContraseñaField.setPromptText("Contraseña");

        TextField[] fields = { identificacionField, nombreField, correoField, telefonoField,
                direccionField, ContraseñaField };

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
    private void CrearUsuario() throws IOException {
        String identificacion = identificacionField.getText();
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String telefono = telefonoField.getText();
        String direccion = direccionField.getText();
        String contraseña = ContraseñaField.getText();

        if (identificacion.isEmpty() || nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()
                || direccion.isEmpty() || contraseña.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, complete todos los campos.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Validación de correo y contraseña
        if (!UsuarioCRUD.esCorreoValido(correo)) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("El correo electrónico no es válido");
            return;
        }

        if (!UsuarioCRUD.esContrasenaValida(contraseña)) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("La contraseña debe contener mínimo una letra y un número");
            return;
        }

        try {
            // Crear UsuarioDto y mapearlo a Usuario
            UsuarioDto usuarioDto = new UsuarioDto(identificacion, nombre, correo, telefono, direccion, contraseña,
                    0.0);
            Usuario usuario = bancoMapper.usuarioDtoToUsuario(usuarioDto);

            billeteraVirtual.getUsuarioCRUD().crear(usuario);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Usuario creado exitosamente.");
            mensajeLabel.setStyle("-fx-text-fill: green;");
        } catch (IllegalArgumentException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("El usuario ya está registrado.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al crear el usuario");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionUsuarios", "Gestión Usuarios");
    }
}
