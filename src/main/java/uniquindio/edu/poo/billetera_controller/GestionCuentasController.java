package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.mapping.dto.CuentaDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class GestionCuentasController {

    @FXML
    private TableView<CuentaDto> tablaCuentas;

    @FXML
    private TableColumn<CuentaDto, String> identificacionField;

    @FXML
    private TableColumn<CuentaDto, String> codigoField;

    @FXML
    private TableColumn<CuentaDto, String> numeroField;

    @FXML
    private TableColumn<CuentaDto, String> bancoField;

    @FXML
    private TableColumn<CuentaDto, String> tipoField;

    @FXML
    private TableColumn<CuentaDto, Double> saldoField;

    private BancoMapper bancoMapper = BancoMapper.INSTANCE;
    private ObservableList<CuentaDto> cuentas = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        Billetera_virtual.getInstancia().getCuentas().stream()
                .map(bancoMapper::cuentaToCuentaDto)
                .forEach(cuentas::add);

        identificacionField
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().idUsuario()));
        codigoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().id()));
        numeroField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().numeroCuenta()));
        bancoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().nombreBanco()));
        tipoField.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().tipoCuenta().toString()));

        saldoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().saldo()));
        saldoField.setCellFactory(column -> {
            return new TableCell<CuentaDto, Double>() {
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

        tablaCuentas.setItems(cuentas);
    }

    @FXML
    private void AgregarCuenta() throws IOException {
        App.setRoot("CreacionCuenta", "Creación Cuenta");
    }

    @FXML
    private void ActualizarCuenta() throws IOException {
        App.setRoot("ActualizacionCuenta", "Actualización Cuenta");
    }

    @FXML
    private void EliminarCuenta() throws IOException {
        App.setRoot("EliminacionCuenta", "Eliminación Cuenta");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Administrador", "Administrador");
    }
}
