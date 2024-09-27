package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Usuario;

public class GestionUsuariosController {

    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    private TableColumn<Usuario, String> identificacionField;

    @FXML
    private TableColumn<Usuario, String> nombreField;

    @FXML
    private TableColumn<Usuario, String> correoField;

    @FXML
    private TableColumn<Usuario, String> telefonoField;

    @FXML
    private TableColumn<Usuario, String> direccionField;

    @FXML
    private TableColumn<Usuario, Double> saldoActualField;

    private ObservableList<Usuario> usuarios = FXCollections
            .observableArrayList(Billetera_virtual.getInstancia().getUsuarios());

    @FXML
    private void CrearUsuario() throws IOException {
        App.setRoot("CreacionUsuario");
    }

    @FXML
    private void ActualizarUsuario() throws IOException {
        App.setRoot("ActualizacionUsuario");
    }

    @FXML
    private void EliminarUsuario() throws IOException {
        App.setRoot("EliminacionUsuario");
    }

    @FXML
    private void BuscarUsuario() throws IOException {
        App.setRoot("LecturaUsuario");
    }

    @FXML
    private void initialize() {
        DecimalFormat formatoDecimal = new DecimalFormat("#");
        identificacionField.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreField.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        correoField.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefonoField.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccionField.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        saldoActualField.setCellValueFactory(new PropertyValueFactory<>("saldoTotal"));
        saldoActualField.setCellFactory(column -> {
            return new TableCell<Usuario, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(formatoDecimal.format(item));
                    }
                }
            };
        });
    }

    @FXML
    private void mostrarUsuarios() throws IOException {

        tablaUsuarios.setItems(usuarios);
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Administrador");
    }
}
