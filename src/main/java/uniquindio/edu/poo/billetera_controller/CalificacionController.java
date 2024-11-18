package uniquindio.edu.poo.billetera_controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_exception.FormatoNumericoIncorrectoException;
import uniquindio.edu.poo.billetera_exception.RangoCalificacionInvalidoException;

public class CalificacionController {

    @FXML
    private TextField CalificacionField;

    @FXML
    private Button EnviarLabel;

    @FXML
    private Button VolverLabel;

    @FXML
    private Label mensajeLabel;

    @FXML
    void Enviar(ActionEvent event) {
        InsertarCalificacion(event);
    }

    @FXML
    void InsertarCalificacion(ActionEvent event) {
        String calificacionTexto = CalificacionField.getText();

        try {

            int calificacion;
            try {
                calificacion = Integer.parseInt(calificacionTexto);
            } catch (NumberFormatException e) {
                throw new FormatoNumericoIncorrectoException("Ingrese un número válido entre 1 y 5.");
            }

            if (calificacion < 1 || calificacion > 5) {
                throw new RangoCalificacionInvalidoException("Por favor ingrese una calificación entre 1 y 5.");
            }

            guardarCalificacionEnArchivo(calificacion);

            mostrarMensaje("¡Gracias por tu calificación!", "green");
            CalificacionField.clear();
        

        } catch (FormatoNumericoIncorrectoException | RangoCalificacionInvalidoException e) {
            mostrarMensaje(e.getMessage(), "red");
        } catch (IOException e) {
            mostrarMensaje("Error al guardar la calificación.", "red");
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje, String color) {
        mensajeLabel.setText(mensaje);
        mensajeLabel.setStyle("-fx-text-fill: " + color + ";");
        mensajeLabel.setVisible(true);
    }

    private void guardarCalificacionEnArchivo(int calificacion) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\td\\persistencia\\archivos\\calificaciones.txt", true))) {
            writer.write("Calificacion: " + calificacion);
            writer.newLine();
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("Usuario", "Usuario");
    }
}
