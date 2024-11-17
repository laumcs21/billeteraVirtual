package uniquindio.edu.poo.billetera_controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import uniquindio.edu.poo.billetera_app.App;

public class EstadisticasController {

    @FXML
    private Button EstadisticaSaldo;

    @FXML
    private Button EstadisticaTransaccion;

    @FXML
    private Button GastosComunes;

    @FXML
    private Button Volver;

    @FXML
    void MostrarEstadisticaSaldo() throws IOException {
        App.setRoot("UsuariosSaldoPromedio", "Usuarios Saldo Promedio");

    }

    @FXML
    void MostrarEstadisticaTransaccion() throws IOException {
        App.setRoot("UsuariosConMasTransacciones", "UsuariosConMasTransacciones");

    }

    @FXML
    private void MostrarGastosComunes() throws IOException{
        App.setRoot("GastosComunes", "Gastos comunes");

    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Administrador", "Administrador");
    }

}

