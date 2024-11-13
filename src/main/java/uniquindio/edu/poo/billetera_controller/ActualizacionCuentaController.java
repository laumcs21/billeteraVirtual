package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.TipoCuenta;
import uniquindio.edu.poo.mapping.dto.CuentaDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class ActualizacionCuentaController {

    @FXML
    private TextField IDusuarioField;

    @FXML
    private TextField IDcuentaField;

    @FXML
    private TextField nombreBancoField;

    @FXML
    private ComboBox<TipoCuenta> tipoCuentaComboBox;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;
    private BancoMapper bancoMapper = BancoMapper.INSTANCE;
    private CuentaDto cuentaEncontradaDto;

    public ActualizacionCuentaController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        IDcuentaField.setPromptText("ID Cuenta");
        nombreBancoField.setPromptText("Nombre Banco");
        IDusuarioField.setPromptText("ID usuario");

        tipoCuentaComboBox.getItems().setAll(TipoCuenta.values());

        TextField[] fields = { IDcuentaField, nombreBancoField, IDusuarioField };

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
    private void Buscar() throws IOException {
        String id = IDcuentaField.getText();

        if (id.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese la identificación de la cuenta.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        try {
            var cuenta = billeteraVirtual.getCuentaCRUD().leer(id);
            if (cuenta != null) {
                cuentaEncontradaDto = bancoMapper.cuentaToCuentaDto(cuenta);
                llenarCamposConCuenta(cuentaEncontradaDto);
                mensajeLabel.setVisible(false);
            } else {
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("La cuenta no se ha creado.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al cargar la cuenta.");
        }
    }

    private void llenarCamposConCuenta(CuentaDto cuentaDto) {
        nombreBancoField.setText(cuentaDto.nombreBanco());
        IDusuarioField.setText(cuentaDto.idUsuario());
        tipoCuentaComboBox.setValue(cuentaDto.tipoCuenta());
    }

    @FXML
    private void Actualizar() throws IOException {
        if (cuentaEncontradaDto == null) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Primero debe buscar una cuenta.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        cuentaEncontradaDto = new CuentaDto(
                IDusuarioField.getText(),
                cuentaEncontradaDto.id(),
                nombreBancoField.getText(),
                cuentaEncontradaDto.numeroCuenta(),
                tipoCuentaComboBox.getValue(),
                cuentaEncontradaDto.saldo());

        try {
            billeteraVirtual.getCuentaCRUD().actualizar(bancoMapper.cuentaDtoToCuenta(cuentaEncontradaDto));
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Cuenta actualizada exitosamente.");
            mensajeLabel.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al actualizar la cuenta.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionCuentas", "Gestión Cuentas");
    }
}
