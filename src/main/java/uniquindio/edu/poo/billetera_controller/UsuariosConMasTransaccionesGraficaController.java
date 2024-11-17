package uniquindio.edu.poo.billetera_controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import uniquindio.edu.poo.billetera_model.UsuarioTransaccionData;

import java.io.IOException;

public class UsuariosConMasTransaccionesGraficaController {

    @FXML
    private BarChart<String, Number> usuariosBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button volverButton;

    public void setDatosUsuarios(ObservableList<UsuarioTransaccionData> topUsuariosData) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Transacciones por Usuario");

        for (UsuarioTransaccionData usuario : topUsuariosData) {
            series.getData().add(new XYChart.Data<>(usuario.getNombreUsuario(), usuario.getCantidadTransacciones()));
        }

        usuariosBarChart.getData().clear();
        usuariosBarChart.getData().add(series);
    }

   @FXML
    private void volver(ActionEvent event) throws IOException {
        ((Button) event.getSource()).getScene().getWindow().hide();
    }
}
