package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;

public class EliminacionCategoriaController {

    @FXML
    private TextField idField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    public EliminacionCategoriaController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        idField.setPromptText("ID Categoría");

        TextField[] fields = { idField };
        idField.clear();

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
        String identificacion = idField.getText();

        if (identificacion.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese una identificación.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            billeteraVirtual.getCategoriaCRUD().eliminar(identificacion);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Categoría eliminada exitosamente.");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("La categoría no existe.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionCategorias", "Gestion Categorias");
    }
}
