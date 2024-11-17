package uniquindio.edu.poo.billetera_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import uniquindio.edu.poo.billetera_model.Transaccion;

import uniquindio.edu.poo.billetera_model.GastoData;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GastosComunesController {

    @FXML
    private Button GraficaGasto;

    @FXML
    private TableColumn<GastoData, String> TipoGastoField;

    @FXML
    private Button Volver;

    @FXML
    private TableColumn<GastoData, Integer> cantidadGastoField;

    @FXML
    private TableView<GastoData> gastosTable;

    private Billetera_virtual billeteraVirtual;

    public GastosComunesController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
        
    }

    @FXML
    private void initialize() {
        
        TipoGastoField.setCellValueFactory(new PropertyValueFactory<>("tipoGasto"));
        cantidadGastoField.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

      
        cargarDatosGastos();
    }

    private void cargarDatosGastos() {
        List<Transaccion> transacciones = billeteraVirtual.getTransacciones();

       
        Map<String, Integer> conteoGastos = new HashMap<>();
        int totalGastos = 0;

        for (Transaccion transaccion : transacciones) {
            String nombreCategoria = transaccion.getNombreCategoria();
            System.out.println("Procesando categoría: " + nombreCategoria); 

            if (nombreCategoria != null && !nombreCategoria.trim().isEmpty()) { 
                                                                                
                conteoGastos.put(nombreCategoria, conteoGastos.getOrDefault(nombreCategoria, 0) + 1);
                totalGastos++;
            } else {
                System.out.println("Categoría ignorada porque está vacía o nula."); 
                                                                                    
            }
        }

        ObservableList<GastoData> data = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : conteoGastos.entrySet()) {
            data.add(new GastoData(entry.getKey(), entry.getValue()));
        }

        data.add(new GastoData("Total", totalGastos));
        gastosTable.setItems(data);

    }

    @FXML
    private void MostrarGraficaGastos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/uniquindio/edu/poo/billetera_view/GastosComunesGrafica.fxml"));
            Parent root = loader.load();

            GastosComunesGraficaController graficaController = loader.getController();

            graficaController.setDatosGastos(gastosTable.getItems());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gráfica de Gastos Comunes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Estadisticas", "Estadisticas");
    }

}