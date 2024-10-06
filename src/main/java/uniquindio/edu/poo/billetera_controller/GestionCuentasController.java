package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Cuenta;
import uniquindio.edu.poo.billetera_model.Usuario;

public class GestionCuentasController {

    @FXML
    private TableView<Cuenta> tablaCuentas;

    @FXML
    private TableColumn<Cuenta, String> identificacionField;

    @FXML
    private TableColumn<Cuenta, String> codigoField;

    @FXML
    private TableColumn<Cuenta, String> numeroField;

    @FXML
    private TableColumn<Cuenta, String> bancoField;

    @FXML
    private TableColumn<Cuenta, String> tipoField;

    @FXML
    private TableColumn<Usuario, Double> saldoActualField;

    private ObservableList<Cuenta> cuentas = FXCollections
            .observableArrayList(Billetera_virtual.getInstancia().getCuentas());

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

    @FXML
    private void initialize() {
        identificacionField.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        codigoField.setCellValueFactory(new PropertyValueFactory<>("id"));
        numeroField.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        bancoField.setCellValueFactory(new PropertyValueFactory<>("nombreBanco"));
        tipoField.setCellValueFactory(new PropertyValueFactory<>("tipoCuenta"));
    }

    @FXML
    private void mostrarCuentas() throws IOException {

        tablaCuentas.setItems(cuentas);
    }
}
