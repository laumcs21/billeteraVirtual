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
import uniquindio.edu.poo.billetera_model.Transaccion;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.billetera_model.UsuarioTransaccionData;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsuariosConMasTransaccionesController {

    @FXML
    private TableView<UsuarioTransaccionData> usuariosTable;

    @FXML
    private TableColumn<UsuarioTransaccionData, String> usuarioColumn;

    @FXML
    private TableColumn<UsuarioTransaccionData, Integer> transaccionesColumn;

    private final Billetera_virtual billeteraVirtual;
    private ObservableList<UsuarioTransaccionData> topUsuariosData;

    public UsuariosConMasTransaccionesController() {
        billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        transaccionesColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadTransacciones"));
        
        cargarDatosUsuarios();
    }

    private void cargarDatosUsuarios() {
        List<Transaccion> transacciones = billeteraVirtual.getTransacciones();

        // Contar las transacciones por usuario
        Map<String, Integer> conteoTransacciones = new HashMap<>();
        for (Transaccion transaccion : transacciones) {
            conteoTransacciones.put(transaccion.getIdUsuario(), conteoTransacciones.getOrDefault(transaccion.getIdUsuario(), 0) + 1);
        }

        // Obtener los cinco usuarios con más transacciones
        topUsuariosData = conteoTransacciones.entrySet().stream()
                .map(entry -> new UsuarioTransaccionData(
                        billeteraVirtual.getUsuarios().stream()
                                .filter(u -> u.getId().equals(entry.getKey()))
                                .findFirst().orElse(new Usuario(entry.getKey(), "", "Desconocido", "", "", "", 0)).getNombre(),
                        entry.getValue()))
                .sorted(Comparator.comparingInt(UsuarioTransaccionData::getCantidadTransacciones).reversed())
                .limit(5)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        usuariosTable.setItems(topUsuariosData);
    }

    @FXML
    private void verGrafica(ActionEvent event) throws IOException {
        // Cargar la vista de la gráfica de usuarios con más transacciones
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/uniquindio/edu/poo/billetera_view/UsuariosConMasTransaccionesGrafica.fxml"));
        Parent root = loader.load();
        
        // Obtener el controlador de la vista cargada
        UsuariosConMasTransaccionesGraficaController graficaController = loader.getController();
        
        // Pasar los datos al controlador de la gráfica
        graficaController.setDatosUsuarios(topUsuariosData); // Aquí usamos topUsuariosData en lugar de datosUsuarios
        
        // Mostrar la nueva ventana con la gráfica
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Gráfica de Usuarios con Más Transacciones");
        stage.show();
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("Estadisticas", "Estadisticas"); // Cambia "VistaAnterior" al nombre de la vista anterior
    }
}



