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
import uniquindio.edu.poo.billetera_model.Transaccion;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.billetera_model.BuscarUsuarioPorID;

public class GestionTransaccionesController {

    @FXML
    private TableView<Transaccion> tablaTransacciones;

    @FXML
    private TableColumn<Transaccion, String> fechaField;

    @FXML
    private TableColumn<Transaccion, String> IDtransaccionField;

    @FXML
    private TableColumn<Transaccion, String> idUsuarioField;

    @FXML
    private TableColumn<Transaccion, String> cuentaOrigenField;

    @FXML
    private TableColumn<Transaccion, String> tipoField;

    @FXML
    private TableColumn<Transaccion, Double> montoField;

    @FXML
    private TableColumn<Transaccion, String> cuentaDestinoField;

    @FXML
    private TableColumn<Transaccion, String> descripcionField;

    @FXML
    private TableColumn<Transaccion, String> categoriaField;

    private static Billetera_virtual billeteraVirtual;
    String idUsuario = Sesion.getIdUsuario();

    private ObservableList<Transaccion> todasTransacciones = FXCollections
            .observableArrayList(Billetera_virtual.getInstancia().getTransacciones());

    private ObservableList<Transaccion> TransaccionesPorUsuario;

    public GestionTransaccionesController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    private void CrearTransaccion() throws IOException {
        App.setRoot("CreacionTransacciones", "Creación Transacciones");
    }

    @FXML
    private void initialize() {
        fechaField.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        IDtransaccionField.setCellValueFactory(new PropertyValueFactory<>("id"));
        idUsuarioField.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        cuentaOrigenField.setCellValueFactory(new PropertyValueFactory<>("numeroCuentaOrigen"));
        tipoField.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        montoField.setCellValueFactory(new PropertyValueFactory<>("monto"));
        cuentaDestinoField.setCellValueFactory(new PropertyValueFactory<>("numeroCuentaDestino"));
        descripcionField.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        categoriaField.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));
    }

    @FXML
    private void mostrarTransacciones() throws IOException {
        if (Sesion.getEsAdmin()) {
            tablaTransacciones.setItems(todasTransacciones);
        } else {

            Usuario usuario = BuscarUsuarioPorID.buscarUsuarioPorIdentificacion(idUsuario);

            if (usuario != null) {
                List<Transaccion> listaTransacciones = billeteraVirtual.getTransacciones().stream()
                        .filter(Transaccion -> Transaccion.getIdUsuario().equals(usuario.getId()))
                        .collect(Collectors.toList());

                if (!listaTransacciones.isEmpty()) {
                    TransaccionesPorUsuario = FXCollections.observableArrayList(listaTransacciones);
                    tablaTransacciones.setItems(TransaccionesPorUsuario);
                } else {
                    System.out.println("No se encontraron transacciones para el usuario con ID: " + usuario.getId());
                }
            } else {
                System.out.println("No se encontró el usuario con ID: " + idUsuario);
            }

        }
    }

    @FXML
    private void Volver() throws IOException {
        if (Sesion.getEsAdmin()) {
            App.setRoot("Administrador", "Administrador");
        } else {
            App.setRoot("Usuario", "Usuario");
        }
    }

}
