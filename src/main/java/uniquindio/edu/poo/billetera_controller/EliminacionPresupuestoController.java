package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_exception.FormatoNumericoIncorrectoException;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;

public class EliminacionPresupuestoController {

    @FXML
    private TextField IdField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    public EliminacionPresupuestoController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        IdField.setPromptText("ID Presupuesto");

        TextField[] fields = { IdField };

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
        String idPresupuesto = IdField.getText();

        if (idPresupuesto == null || idPresupuesto.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese la identificación del presupuesto.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {

            if (!idPresupuesto.matches("\\d+")) {
                throw new FormatoNumericoIncorrectoException("El ID de presupuesto debe contener solo números.");
            }
            billeteraVirtual.getPresupuestoCRUD().eliminar(idPresupuesto);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Presupuesto eliminado exitosamente.");
            mensajeLabel.setStyle("-fx-text-fill: green;");

        } catch (FormatoNumericoIncorrectoException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText(e.getMessage());
            mensajeLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("El presupuesto no existe o no se pudo eliminar.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionPresupuestos", "Gestión de Presupuestos");
    }
}
