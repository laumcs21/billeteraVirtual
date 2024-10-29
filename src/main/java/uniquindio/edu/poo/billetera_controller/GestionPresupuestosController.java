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
import uniquindio.edu.poo.billetera_model.Presupuesto;
import uniquindio.edu.poo.billetera_model.Sesion;

public class GestionPresupuestosController {

    @FXML
    private TableView<Presupuesto> TablaPresupuestos;

    @FXML
    private TableColumn<Presupuesto, String> IdField;

    @FXML
    private TableColumn<Presupuesto, String> NombreField;

    @FXML
    private TableColumn<Presupuesto, Double> MontoField;

    @FXML
    private TableColumn<Presupuesto, Double> MontoGastadoField;

    @FXML
    private TableColumn<Presupuesto, String> CategoriaField;

    private static Billetera_virtual billeteraVirtual;
    private ObservableList<Presupuesto> presupuestosPorUsuario;

    public GestionPresupuestosController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    private void initialize() {

        IdField.setCellValueFactory(new PropertyValueFactory<>("id"));
        NombreField.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        MontoField.setCellValueFactory(new PropertyValueFactory<>("monto"));
        MontoGastadoField.setCellValueFactory(new PropertyValueFactory<>("montoGastado"));
        CategoriaField.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
    }

    @FXML
    private void CrearPresupuesto() throws IOException {
        App.setRoot("CreacionPresupuesto", "Creación Presupuesto");
    }

    @FXML
    private void ActualizarPresupuesto() throws IOException {
        App.setRoot("ActualizacionPresupuesto", "Actualización Presupuesto");
    }

    @FXML
    private void EliminarPresupuesto() throws IOException {
        App.setRoot("EliminacionPresupuesto", "Eliminación Presupuesto");
    }

    @FXML
    private void MostrarPresupuestos() {
        String idUsuario = Sesion.getIdUsuario();

        List<Presupuesto> listaPresupuestos = billeteraVirtual.getPresupuestos().stream()
                .filter(presupuesto -> presupuesto.getIdUsuario().equals(idUsuario))
                .collect(Collectors.toList());

        if (!listaPresupuestos.isEmpty()) {
            presupuestosPorUsuario = FXCollections.observableArrayList(listaPresupuestos);
            TablaPresupuestos.setItems(presupuestosPorUsuario);
        } else {
            System.out.println("No se encontraron presupuestos para el usuario con ID: " + idUsuario);
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Usuario", "Usuario");
    }
}
