package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;

public class EliminacionCuentaController {

    @FXML
    private TextField IDcuentaField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    public EliminacionCuentaController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        IDcuentaField.setPromptText("ID Cuenta");

        TextField[] fields = { IDcuentaField };

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
    private void Eliminar() throws IOException {
        String id = IDcuentaField.getText();

        if (id.isEmpty() || id == null) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese la identificación de la cuenta.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        try {
            billeteraVirtual.getCuentaCRUD().eliminar(id);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Cuenta eliminada exitosamente.");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("La cuenta no se ha creado.");
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionCuentas", "Gestión Cuentas");
    }

}
