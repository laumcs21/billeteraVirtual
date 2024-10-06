package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Cuenta;
import uniquindio.edu.poo.billetera_model.GeneradorNumeroCuenta;
import uniquindio.edu.poo.billetera_model.TipoCuenta;
import uniquindio.edu.poo.billetera_model.GeneradorCodigoCuenta;

public class CreacionCuentaController {

    @FXML
    private TextField IDusuarioField;

    @FXML
    private TextField IDcuentaField;

    @FXML
    private TextField nombreBancoField;

    @FXML
    private TextField numeroCuentaField;

    @FXML
    private ComboBox<TipoCuenta> tipoCuentaComboBox;

    @FXML
    private Label mensajeLabel1;

    @FXML
    private Label mensajeLabel2;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    public CreacionCuentaController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel1.setVisible(true);
        mensajeLabel2.setVisible(true);
        mensajeLabel.setVisible(false);
        nombreBancoField.setPromptText("Nombre Banco");
        IDusuarioField.setPromptText("ID usuario");

        tipoCuentaComboBox.getItems().setAll(TipoCuenta.values());

        TextField[] fields = { nombreBancoField, IDusuarioField };

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
    private void Crear() throws IOException {
        String nombreBanco = nombreBancoField.getText();
        String idUsuario = IDusuarioField.getText();
        TipoCuenta tipoCuentaSeleccionado = tipoCuentaComboBox.getValue();

        if (nombreBanco.isEmpty() || idUsuario.isEmpty() || tipoCuentaSeleccionado == null) {
            mensajeLabel.setText("Por favor, complete todos los campos.");
            mensajeLabel.setVisible(true);
            return;
        }

        List<Cuenta> cuentasExistentes = billeteraVirtual.getCuentas();

        String idCuentaUnico = GeneradorCodigoCuenta.generarCodigoUnico(5, cuentasExistentes);
        String numeroCuentaUnico = GeneradorNumeroCuenta.generarNumeroUnico(10, cuentasExistentes);

        Cuenta nuevaCuenta = new Cuenta(idUsuario, idCuentaUnico, nombreBanco, numeroCuentaUnico,
                tipoCuentaSeleccionado);

        billeteraVirtual.getCuentaCRUD().crear(nuevaCuenta);

        IDcuentaField.setText(idCuentaUnico);
        numeroCuentaField.setText(numeroCuentaUnico);

        mensajeLabel
                .setText("Cuenta creada con éxito.");
        mensajeLabel.setVisible(true);
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionCuentas", "Gestión Cuentas");
    }

}
