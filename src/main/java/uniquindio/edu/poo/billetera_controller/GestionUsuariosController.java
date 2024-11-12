package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.mapping.dto.UsuarioDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class GestionUsuariosController {

    @FXML
    private TableView<UsuarioDto> tablaUsuarios;

    @FXML
    private TableColumn<UsuarioDto, String> identificacionField;

    @FXML
    private TableColumn<UsuarioDto, String> nombreField;

    @FXML
    private TableColumn<UsuarioDto, String> correoField;

    @FXML
    private TableColumn<UsuarioDto, String> telefonoField;

    @FXML
    private TableColumn<UsuarioDto, String> direccionField;

    @FXML
    private TableColumn<UsuarioDto, Double> saldoActualField;

    // Instancia del mapper para convertir entidades a DTOs
    private BancoMapper mapper = BancoMapper.INSTANCE;

    private ObservableList<UsuarioDto> usuarios = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        DecimalFormat formatoDecimal = new DecimalFormat("#");

        // Configuración personalizada para cada columna usando un Callback
        identificacionField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().id()));
        nombreField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().nombre()));
        correoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().correo()));
        telefonoField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().telefono()));
        direccionField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().direccion()));

        // Usar un formato especial para la columna saldoActualField
        saldoActualField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().saldoTotal()));
        saldoActualField.setCellFactory(column -> {
            return new TableCell<UsuarioDto, Double>() {
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
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        // Mapear cada Usuario a UsuarioDto antes de agregarlo a la lista
        Billetera_virtual.getInstancia().getUsuarios().stream()
                .map(mapper::usuarioToUsuarioDto) // Conversión a DTO
                .forEach(usuarios::add);
        tablaUsuarios.setItems(usuarios);
    }

    @FXML
    private void CrearUsuario() throws IOException {
        App.setRoot("CreacionUsuario", "Creación Usuario");
    }

    @FXML
    private void ActualizarUsuario() throws IOException {
        App.setRoot("ActualizacionUsuario", "Actualización Usuario");
    }

    @FXML
    private void EliminarUsuario() throws IOException {
        App.setRoot("EliminacionUsuario", "Eliminación Usuario");
    }

    @FXML
    private void BuscarUsuario() throws IOException {
        App.setRoot("LecturaUsuario", "Lectura Usuario");
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Administrador", "Administrador");
    }
}
