package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.BuscarCategoria;
import uniquindio.edu.poo.mapping.dto.PresupuestoDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class GestionPresupuestosController {

    @FXML
    private TableView<PresupuestoDto> tablaPresupuestos;

    @FXML
    private TableColumn<PresupuestoDto, String> idField;

    @FXML
    private TableColumn<PresupuestoDto, String> nombreField;

    @FXML
    private TableColumn<PresupuestoDto, Double> montoField;

    @FXML
    private TableColumn<PresupuestoDto, Double> montoGastadoField;

    @FXML
    private TableColumn<PresupuestoDto, String> categoriaField;

    private BancoMapper bancoMapper = BancoMapper.INSTANCE;
    private ObservableList<PresupuestoDto> presupuestos = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        // Convertir presupuestos a PresupuestoDto y agregar a la lista observable
        Billetera_virtual.getInstancia().getPresupuestos().stream()
                .map(bancoMapper::presupuestoToPresupuestoDto)
                .forEach(presupuestos::add);

        idField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().id()));
        nombreField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().nombre()));
        montoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().monto()));
        montoGastadoField
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().montoGastado()));

        montoField.setCellFactory(column -> {
            return new TableCell<PresupuestoDto, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(String.format("%.2f", item));
                    }
                }
            };
        });

        montoGastadoField.setCellFactory(column -> {
            return new TableCell<PresupuestoDto, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(String.format("%.2f", item));
                    }
                }
            };
        });

        // Columna de categoría usando SimpleStringProperty para buscar nombre de la
        // categoría
        categoriaField.setCellValueFactory(cellData -> {
            String idCategoria = cellData.getValue().idCategoria();
            String nombreCategoria = BuscarCategoria.buscarCategoriaPorID(idCategoria) != null
                    ? BuscarCategoria.buscarCategoriaPorID(idCategoria).getNombre()
                    : "Categoría desconocida";
            return new SimpleStringProperty(nombreCategoria);
        });

        // Asignar lista observable a la tabla
        tablaPresupuestos.setItems(presupuestos);
    }

    @FXML
    private void CrearPresupuesto() throws IOException {
        App.setRoot("CreacionPresupuesto", "Creación Presupuesto");
    }

    @FXML
    private void ActualizarPresupuesto() throws IOException {
        App.setRoot("ActualizacionPresupuesto", "Actualización Presupuesto");
    }

    @FXML
    private void EliminarPresupuesto() throws IOException {
        App.setRoot("EliminacionPresupuesto", "Eliminación Presupuesto");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Usuario", "Usuario");
    }
}
