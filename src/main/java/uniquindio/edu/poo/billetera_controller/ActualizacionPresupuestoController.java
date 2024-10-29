package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.BuscarCategoria;
import uniquindio.edu.poo.billetera_model.Categoria;
import uniquindio.edu.poo.billetera_model.Presupuesto;

public class ActualizacionPresupuestoController {

    @FXML
    private TextField IdField;

    @FXML
    private TextField NombreField;

    @FXML
    private TextField MontoField;

    @FXML
    private ComboBox<String> Categoria;

    @FXML
    private Label mensajeLabel;

    private Presupuesto presupuestoEncontrado;

    private Billetera_virtual billeteraVirtual;

    public ActualizacionPresupuestoController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        IdField.setPromptText("ID Presupuesto");
        NombreField.setPromptText("Nombre");
        MontoField.setPromptText("Monto");

        billeteraVirtual.getCategorias().forEach(categoria -> Categoria.getItems().add(categoria.getNombre()));

        TextField[] fields = { IdField, NombreField, MontoField };
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
        String idPresupuesto = IdField.getText();

        if (idPresupuesto.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese la identificación del presupuesto.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            presupuestoEncontrado = billeteraVirtual.getPresupuestoCRUD().leer(idPresupuesto);
            if (presupuestoEncontrado != null) {
                llenarCamposConPresupuesto(presupuestoEncontrado);
                mensajeLabel.setVisible(false);
            } else {
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("El presupuesto no está registrado.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al buscar el presupuesto.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void llenarCamposConPresupuesto(Presupuesto presupuesto) {
        NombreField.setText(presupuesto.getNombre());
        MontoField.setText(String.valueOf(presupuesto.getMonto()));
        Categoria.setValue(BuscarCategoria.buscarCategoriaPorID(presupuesto.getIdCategoria()).getNombre());
    }

    @FXML
    private void Actualizar() throws IOException {
        if (presupuestoEncontrado == null) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Primero debe buscar un presupuesto.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Actualizar los campos del presupuesto
            presupuestoEncontrado.setNombre(NombreField.getText());
            presupuestoEncontrado.setMonto(Double.parseDouble(MontoField.getText()));

            // Obtener la categoría seleccionada y su ID
            String categoriaSeleccionada = Categoria.getValue();
            Optional<Categoria> categoriaOpt = billeteraVirtual.getCategorias().stream()
                    .filter(categoria -> categoria.getNombre().equals(categoriaSeleccionada))
                    .findFirst();

            if (categoriaOpt.isPresent()) {
                presupuestoEncontrado.setIdCategoria(categoriaOpt.get().getId());
            } else {
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("Categoría seleccionada no válida.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            billeteraVirtual.getPresupuestoCRUD().actualizar(presupuestoEncontrado);
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Presupuesto actualizado exitosamente.");
            mensajeLabel.setStyle("-fx-text-fill: green;");
        } catch (NumberFormatException e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("El monto debe ser un valor numérico.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Error al actualizar el presupuesto.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionPresupuestos", "Gestión de Presupuestos");
    }
}
