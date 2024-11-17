package uniquindio.edu.poo.billetera_controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import uniquindio.edu.poo.billetera_model.UsuarioSaldoData;

import java.io.IOException;

public class UsuariosSaldoPromedioGraficaController {

    @FXML
    private BarChart<String, Number> usuariosBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button volverButton;

    public void setDatosUsuarios(ObservableList<UsuarioSaldoData> usuariosData) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Saldo por Usuario");
        for (UsuarioSaldoData usuario : usuariosData) {
            series.getData().add(new XYChart.Data<>(usuario.getNombreUsuario(), usuario.getSaldo()));
        }
        usuariosBarChart.getData().add(series);
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        ((Button) event.getSource()).getScene().getWindow().hide();
    }
}
