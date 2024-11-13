package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.mapping.dto.TransaccionDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class GestionTransaccionesController {

    @FXML
    private TableView<TransaccionDto> tablaTransacciones;

    @FXML
    private TableColumn<TransaccionDto, String> fechaField;

    @FXML
    private TableColumn<TransaccionDto, String> IDtransaccionField;

    @FXML
    private TableColumn<TransaccionDto, String> idUsuarioField;

    @FXML
    private TableColumn<TransaccionDto, String> cuentaOrigenField;

    @FXML
    private TableColumn<TransaccionDto, String> tipoField;

    @FXML
    private TableColumn<TransaccionDto, Double> montoField;

    @FXML
    private TableColumn<TransaccionDto, String> cuentaDestinoField;

    @FXML
    private TableColumn<TransaccionDto, String> descripcionField;

    @FXML
    private TableColumn<TransaccionDto, String> categoriaField;

    private BancoMapper mapper = BancoMapper.INSTANCE;
    private ObservableList<TransaccionDto> todasTransacciones = FXCollections.observableArrayList();
    private ObservableList<TransaccionDto> transaccionesPorUsuario;
    private String idUsuario = Sesion.getIdUsuario();

    public GestionTransaccionesController() {
        cargarTransacciones();
    }

    @FXML
    private void initialize() {
        DecimalFormat formatoDecimal = new DecimalFormat("#.00");

        fechaField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().fecha().toString()));
        IDtransaccionField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().id()));
        idUsuarioField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().idUsuario()));
        cuentaOrigenField
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().numeroCuentaOrigen()));
        tipoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().tipo().toString()));
        montoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().monto()));
        montoField.setCellFactory(column -> new TableCell<TransaccionDto, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : formatoDecimal.format(item));
            }
        });
        cuentaDestinoField.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().numeroCuentaDestino()));
        descripcionField
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().descripcion()));
        categoriaField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().idCategoria()));

    }

    private void cargarTransacciones() {
        Billetera_virtual.getInstancia().getTransacciones().stream()
                .map(mapper::transaccionToTransaccionDto)
                .forEach(todasTransacciones::add);
    }

    @FXML
    private void mostrarTransacciones() throws IOException {
        if (Sesion.getEsAdmin()) {
            tablaTransacciones.setItems(todasTransacciones);
        } else {
            List<TransaccionDto> listaTransacciones = todasTransacciones.stream()
                    .filter(transaccion -> transaccion.idUsuario().equals(idUsuario))
                    .collect(Collectors.toList());

            transaccionesPorUsuario = FXCollections.observableArrayList(listaTransacciones);
            tablaTransacciones.setItems(transaccionesPorUsuario);
        }
    }

    @FXML
    private void CrearTransaccion() throws IOException {
        App.setRoot("CreacionTransacciones", "Creación Transacciones");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot(Sesion.getEsAdmin() ? "Administrador" : "Usuario",
                Sesion.getEsAdmin() ? "Administrador" : "Usuario");
    }
}
