package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.List;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.GeneradorCodigoCategoria;
import uniquindio.edu.poo.billetera_model.Categoria;

public class CreacionCategoriaController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextArea descripcionField;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    public CreacionCategoriaController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        nombreField.setPromptText("ID usuario");
        descripcionField.setPromptText("Descripción (opcional)");

        TextField[] fields = { nombreField };

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
        campoTexto.setPromptText(campoTexto.getPromptText());
    }

    private void limpiarCampoTextoArea(TextArea campoTexto) {
        campoTexto.clear();
    }

    @FXML
    private void Crear() throws IOException {
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();

        if (nombre.isEmpty()) {
            mensajeLabel.setText("El nombre es obligatorio.");
            mensajeLabel.setVisible(true);
            return;
        }

        List<Categoria> categoriasExistentes = billeteraVirtual.getCategorias();
        String idCategoriaUnico = GeneradorCodigoCategoria.generarCodigoUnico(5, categoriasExistentes);

        Categoria categoria = new Categoria.Builder(idCategoriaUnico, nombre)
                .conDescripcion(
                        descripcion.isEmpty() || descripcion.equals("Descripción (opcional)") ? null : descripcion)
                .build();

        billeteraVirtual.getCategoriaCRUD().crear(categoria);
        mensajeLabel.setText("Categoría creada con éxito.");
        mensajeLabel.setVisible(true);

    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionCategorias", "Gestión Categorías");
    }
}
