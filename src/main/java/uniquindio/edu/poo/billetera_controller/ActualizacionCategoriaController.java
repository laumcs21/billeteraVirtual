package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Categoria;

public class ActualizacionCategoriaController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextArea descripcionField;

    @FXML
    private Label mensajeLabel;

    private Categoria categoriaEncontrada;

    private Billetera_virtual billeteraVirtual;

    public ActualizacionCategoriaController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        idField.setPromptText("ID Categoría");
        nombreField.setPromptText("Nombre");

        TextField[] fields = { idField, nombreField };
        idField.clear();

        for (TextField field : fields) {
            field.setOnMouseClicked(event -> limpiarCampoTexto(field));
            field.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue && field.getText().isEmpty()) {
                    field.setPromptText(field.getPromptText());
                }
            });
        }

        descripcionField.setOnMouseClicked(event -> limpiarCampoTextoArea(descripcionField));
    }

    private void limpiarCampoTexto(TextField campoTexto) {
        campoTexto.clear();
    }

    private void limpiarCampoTextoArea(TextArea campoTexto) {
        campoTexto.clear();
    }

    @FXML
    private void Buscar() throws IOException {
        String identificacion = idField.getText();

        if (identificacion.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese una identificación.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            categoriaEncontrada = billeteraVirtual.getCategoriaCRUD().leer(identificacion);
            if (categoriaEncontrada != null) {
                llenarCamposConCategoria(categoriaEncontrada);
                mensajeLabel.setVisible(false);
            } else {
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("La categoría no está registrado.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al buscar la categoría.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void llenarCamposConCategoria(Categoria categoria) {
        nombreField.setText(categoria.getNombre());
        descripcionField.setText(categoria.getDescripcion());
    }

    @FXML
    private void Actualizar() throws IOException {
        if (categoriaEncontrada == null) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Primero debe buscar una categoría.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        categoriaEncontrada.setNombre(nombreField.getText());
        categoriaEncontrada.setDescripcion(descripcionField.getText());

        try {
            billeteraVirtual.getCategoriaCRUD().actualizar(categoriaEncontrada);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Categoría actualizada exitosamente.");
            mensajeLabel.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al actualizar la categoría.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionCategorias", "Gestion Categorias");
    }
}
