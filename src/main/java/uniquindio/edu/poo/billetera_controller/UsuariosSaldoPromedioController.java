package uniquindio.edu.poo.billetera_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.billetera_model.UsuarioSaldoData;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UsuariosSaldoPromedioController {

    @FXML
    private TableView<UsuarioSaldoData> usuariosTable;

    @FXML
    private TableColumn<UsuarioSaldoData, String> nombreColumn;

    @FXML
    private TableColumn<UsuarioSaldoData, Double> saldoColumn;

    @FXML
    private TableColumn<UsuarioSaldoData, Double> promedioColumn;

    private final Billetera_virtual billeteraVirtual;
    private ObservableList<UsuarioSaldoData> usuariosData;

    public UsuariosSaldoPromedioController() {
        billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        saldoColumn.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        promedioColumn.setCellValueFactory(new PropertyValueFactory<>("promedio"));

        cargarDatosUsuarios();
    }

    private void cargarDatosUsuarios() {
        List<Usuario> usuarios = billeteraVirtual.getUsuarios();
        double saldoTotal = usuarios.stream().mapToDouble(Usuario::getSaldoTotal).sum();
        double promedioSaldo = saldoTotal / usuarios.size();

        usuariosData = usuarios.stream()
                .map(usuario -> new UsuarioSaldoData(usuario.getNombre(), usuario.getSaldoTotal(), promedioSaldo))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        usuariosTable.setItems(usuariosData);
    }

    @FXML
    private void verGrafica(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/uniquindio/edu/poo/billetera_view/UsuariosSaldoPromedioGrafica.fxml"));
        Parent root = loader.load();

        UsuariosSaldoPromedioGraficaController graficaController = loader.getController();
        graficaController.setDatosUsuarios(usuariosData);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Gráfica de Saldos de Usuarios");
        stage.show();
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("Estadisticas", "Estadisticas");
    }
}

