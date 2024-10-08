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
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Cuenta;
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.billetera_model.Usuario;

public class BusquedaCuentasUsuarioController {

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
    private TableColumn<Usuario, Double> saldoField;

    private static Billetera_virtual billeteraVirtual;
    String idUsuario = Sesion.getIdUsuario();

    private ObservableList<Cuenta> cuentas;

    public BusquedaCuentasUsuarioController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    private void initialize() {
        identificacionField.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        codigoField.setCellValueFactory(new PropertyValueFactory<>("id"));
        numeroField.setCellValueFactory(new PropertyValueFactory<>("numeroCuenta"));
        bancoField.setCellValueFactory(new PropertyValueFactory<>("nombreBanco"));
        tipoField.setCellValueFactory(new PropertyValueFactory<>("tipoCuenta"));
        saldoField.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        mostrarCuentas();
    }

    public static Usuario buscarUsuarioPorIdentificacion(String identificacion) {
        return buscarUsuarioRecursivo(billeteraVirtual.getUsuarios(), identificacion, 0);
    }

    private static Usuario buscarUsuarioRecursivo(List<Usuario> usuarios, String identificacion, int indice) {
        if (indice >= usuarios.size()) {
            return null;
        }

        Usuario usuario = usuarios.get(indice);
        if (usuario.getId().equals(identificacion)) {
            return usuario;
        }

        return buscarUsuarioRecursivo(usuarios, identificacion, indice + 1);
    }

    @FXML
    private void mostrarCuentas() {

        Usuario usuario = buscarUsuarioPorIdentificacion(idUsuario);

        if (usuario != null) {
            List<Cuenta> listaCuentas = billeteraVirtual.getCuentas().stream()
                    .filter(cuenta -> cuenta.getIdUsuario().equals(usuario.getId()))
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
