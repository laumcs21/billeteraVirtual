package uniquindio.edu.poo.billetera_controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import uniquindio.edu.poo.billetera_model.GastoData;

import java.io.IOException;

public class GastosComunesGraficaController {

    @FXML
    private BarChart<String, Number> gastosBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button volverButton;

    private ObservableList<GastoData> datosGastos;

    
    public void setDatosGastos(ObservableList<GastoData> datosGastos) {
        if (datosGastos == null || datosGastos.isEmpty()) {
            System.out.println("Los datos proporcionados para la gráfica están vacíos o son nulos.");
        } else {
            System.out.println("Recibiendo datos para la gráfica con " + datosGastos.size() + " elementos.");
        }
        
        this.datosGastos = datosGastos;
        cargarGrafica();
    }
    

    @FXML
    private void initialize() {
        System.out.println("Inicializando gráfico de gastos...");
        xAxis.setLabel("Tipo de Gasto");
        yAxis.setLabel("Cantidad");

       
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(30); 
        yAxis.setTickUnit(5);
    }

    private void cargarGrafica() {
        if (datosGastos == null || datosGastos.isEmpty()) {
            System.out.println("datosGastos está vacío o es nulo.");
            return;
        }
        System.out.println("cargarGrafica() fue llamado");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Gastos Comunes");

        for (GastoData gasto : datosGastos) {
            if (!gasto.getTipoGasto().equals("Total")) { 
                System.out.println("Añadiendo a la gráfica: " + gasto.getTipoGasto() + " - " + gasto.getCantidad());
                series.getData().add(new XYChart.Data<>(gasto.getTipoGasto(), gasto.getCantidad()));
            }
        }

        
        gastosBarChart.getData().clear();
        gastosBarChart.getData().add(series);
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        ((Button) event.getSource()).getScene().getWindow().hide();
    }
}
