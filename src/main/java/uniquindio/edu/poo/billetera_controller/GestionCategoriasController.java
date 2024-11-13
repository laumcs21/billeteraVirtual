package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.mapping.dto.CategoriaDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class GestionCategoriasController {

    @FXML
    private TableView<CategoriaDto> tablaCategorias;

    @FXML
    private TableColumn<CategoriaDto, String> idField;

    @FXML
    private TableColumn<CategoriaDto, String> nombreField;

    @FXML
    private TableColumn<CategoriaDto, String> descripcionField;

    private BancoMapper mapper = BancoMapper.INSTANCE;

    private ObservableList<CategoriaDto> categorias = FXCollections.observableArrayList();

    private void cargarCategorias() {
        Billetera_virtual.getInstancia().getCategorias().stream()
                .map(mapper::categoriaToCategoriaDto)
                .forEach(categorias::add);
        tablaCategorias.setItems(categorias);
    }

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
        idField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().id()));
        nombreField.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().nombre()));
        descripcionField
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().descripcion()));
        cargarCategorias();
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Administrador", "Administrador");
    }
}
