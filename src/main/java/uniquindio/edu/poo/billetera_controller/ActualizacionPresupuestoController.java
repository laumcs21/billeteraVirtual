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
import uniquindio.edu.poo.mapping.dto.PresupuestoDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class ActualizacionPresupuestoController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField montoField;

    @FXML
    private ComboBox<String> categoriaComboBox;

    @FXML
    private Label mensajeLabel;

    private PresupuestoDto presupuestoEncontradoDto;

    private Billetera_virtual billeteraVirtual;
    private BancoMapper bancoMapper = BancoMapper.INSTANCE;

    public ActualizacionPresupuestoController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        idField.setPromptText("ID Presupuesto");
        nombreField.setPromptText("Nombre");
        montoField.setPromptText("Monto");

        billeteraVirtual.getCategorias().forEach(categoria -> categoriaComboBox.getItems().add(categoria.getNombre()));

        TextField[] fields = { idField, nombreField, montoField };
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
        String idPresupuesto = idField.getText();

        if (idPresupuesto.isEmpty()) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Por favor, ingrese la identificación del presupuesto.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            Presupuesto presupuesto = billeteraVirtual.getPresupuestoCRUD().leer(idPresupuesto);
            if (presupuesto != null) {
                presupuestoEncontradoDto = bancoMapper.presupuestoToPresupuestoDto(presupuesto);
                llenarCamposConPresupuesto(presupuestoEncontradoDto);
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

    private void llenarCamposConPresupuesto(PresupuestoDto presupuestoDto) {
        nombreField.setText(presupuestoDto.nombre());
        montoField.setText(String.valueOf(presupuestoDto.monto()));
        Categoria categoria = BuscarCategoria.buscarCategoriaPorID(presupuestoDto.idCategoria());
        categoriaComboBox.setValue(categoria != null ? categoria.getNombre() : "");
    }

    @FXML
    private void Actualizar() throws IOException {
        if (presupuestoEncontradoDto == null) {
            mensajeLabel.setVisible(true);
            mensajeLabel.setText("Primero debe buscar un presupuesto.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            String nombre = nombreField.getText();
            double monto = Double.parseDouble(montoField.getText());
            String categoriaSeleccionada = categoriaComboBox.getValue();

            Optional<Categoria> categoriaOpt = billeteraVirtual.getCategorias().stream()
                    .filter(categoria -> categoria.getNombre().equals(categoriaSeleccionada))
                    .findFirst();

            if (categoriaOpt.isEmpty()) {
                mensajeLabel.setVisible(true);
                mensajeLabel.setText("Categoría seleccionada no válida.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            String idCategoria = categoriaOpt.get().getId();
            PresupuestoDto actualizadoPresupuestoDto = new PresupuestoDto(
                    presupuestoEncontradoDto.idUsuario(),
                    presupuestoEncontradoDto.id(),
                    nombre,
                    monto,
                    idCategoria,
                    presupuestoEncontradoDto.montoGastado());

            billeteraVirtual.getPresupuestoCRUD()
                    .actualizar(bancoMapper.presupuestoDtoToPresupuesto(actualizadoPresupuestoDto));
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
