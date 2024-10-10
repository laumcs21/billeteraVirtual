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
import uniquindio.edu.poo.billetera_model.Categoria;

public class GestionCategoriasController {

    @FXML
    private TableView<Categoria> tablaCategorias;

    @FXML
    private TableColumn<Categoria, String> idField;

    @FXML
    private TableColumn<Categoria, String> nombreField;

    @FXML
    private TableColumn<Categoria, String> descripcionField;

    private ObservableList<Categoria> categorias = FXCollections
            .observableArrayList(Billetera_virtual.getInstancia().getCategorias());

    @FXML
    private void CrearCategoria() throws IOException {
        App.setRoot("CreacionCategoria", "Creación Categoria");
    }

    @FXML
    private void ActualizarCategoria() throws IOException {
        App.setRoot("ActualizacionCategoria", "Actualización Categoria");
    }

    @FXML
    private void EliminarCategoria() throws IOException {
        App.setRoot("EliminacionCategoria", "Eliminación Categoria");
    }

    @FXML
    private void initialize() {
        idField.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreField.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcionField.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

    @FXML
    private void mostrarCategorias() throws IOException {
        tablaCategorias.setItems(categorias);
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Administrador", "Administrador");
    }
}
