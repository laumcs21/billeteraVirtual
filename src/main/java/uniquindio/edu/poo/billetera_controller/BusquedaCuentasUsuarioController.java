package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.beans.property.ReadOnlyObjectWrapper;

import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.mapping.dto.CuentaDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class BusquedaCuentasUsuarioController {

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

    private Billetera_virtual billeteraVirtual;
    private BancoMapper bancoMapper = BancoMapper.INSTANCE;
    private String idUsuario = Sesion.getIdUsuario();
    private ObservableList<CuentaDto> cuentas;

    public BusquedaCuentasUsuarioController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    private void initialize() {
        identificacionField
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().idUsuario()));
        codigoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().id()));
        numeroField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().numeroCuenta()));
        bancoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().nombreBanco()));
        tipoField.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().tipoCuenta().toString()));
        saldoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().saldo()));
    }

    private Usuario buscarUsuarioPorIdentificacion(String identificacion) {
        return billeteraVirtual.getUsuarios().stream()
                .filter(usuario -> usuario.getId().equals(identificacion))
                .findFirst()
                .orElse(null);
    }

    @FXML
    private void mostrarCuentas() {
        Usuario usuario = buscarUsuarioPorIdentificacion(idUsuario);

        if (usuario != null) {
            List<CuentaDto> listaCuentas = billeteraVirtual.getCuentas().stream()
                    .filter(cuenta -> cuenta.getIdUsuario().equals(usuario.getId()))
                    .map(bancoMapper::cuentaToCuentaDto)
                    .collect(Collectors.toList());

            if (!listaCuentas.isEmpty()) {
                cuentas = FXCollections.observableArrayList(listaCuentas);
                tablaCuentas.setItems(cuentas);
            } else {
                System.out.println("No se encontraron cuentas para el usuario con ID: " + usuario.getId());
            }
        } else {
            System.out.println("No se encontró el usuario con ID: " + idUsuario);
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Usuario", "Usuario");
    }
}
