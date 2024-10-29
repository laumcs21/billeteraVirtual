package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.BuscarUsuarioPorID;
import uniquindio.edu.poo.billetera_model.Categoria;
import uniquindio.edu.poo.billetera_model.GeneradorCodigoPresupuesto;
import uniquindio.edu.poo.billetera_model.Presupuesto;
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.billetera_model.Usuario;

public class CreacionPresupuestoController {

    @FXML
    private TextField NombreField;

    @FXML
    private TextField MontoField;

    @FXML
    private ComboBox<String> Categoria;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    public CreacionPresupuestoController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        NombreField.setPromptText("Nombre del Presupuesto");
        MontoField.setPromptText("Monto");

        billeteraVirtual.getCategorias().forEach(categoria -> Categoria.getItems().add(categoria.getNombre()));

        NombreField.setOnMouseClicked(event -> limpiarCampoTexto(NombreField));
        MontoField.setOnMouseClicked(event -> limpiarCampoTexto(MontoField));
    }

    private void limpiarCampoTexto(TextField campoTexto) {
        campoTexto.clear();
    }

    @FXML
    private void Crear() throws IOException {
        String idUsuario = Sesion.getIdUsuario();
        String nombre = NombreField.getText();
        String categoriaSeleccionada = Categoria.getValue();
        double monto;

        if (nombre.isEmpty() || MontoField.getText().isEmpty() || categoriaSeleccionada == null) {
            mensajeLabel.setText("Por favor, complete todos los campos obligatorios.");
            mensajeLabel.setVisible(true);
            return;
        }

        try {
            monto = Double.parseDouble(MontoField.getText());
        } catch (NumberFormatException e) {
            mensajeLabel.setText("Monto inválido.");
            mensajeLabel.setVisible(true);
            return;
        }

        Optional<Categoria> categoriaOpt = billeteraVirtual.getCategorias().stream()
                .filter(categoria -> categoria.getNombre().equals(categoriaSeleccionada))
                .findFirst();

        if (!categoriaOpt.isPresent()) {
            mensajeLabel.setText("La categoría seleccionada no es válida.");
            mensajeLabel.setVisible(true);
            return;
        }

        String codigoCategoria = categoriaOpt.get().getId();

        String idPresupuestoUnico = GeneradorCodigoPresupuesto.generarCodigoUnico(5,
                billeteraVirtual.getPresupuestos());

        Presupuesto nuevoPresupuesto = new Presupuesto(idUsuario, idPresupuestoUnico, nombre, monto, codigoCategoria);

        billeteraVirtual.getPresupuestoCRUD().crear(nuevoPresupuesto);
        mensajeLabel.setText("Presupuesto creado con éxito.");
        mensajeLabel.setVisible(true);

        Usuario usuario = BuscarUsuarioPorID.buscarUsuarioPorIdentificacion(idUsuario);
        if (usuario != null) {
            usuario.actualizarSaldoTotal();
            billeteraVirtual.getUsuarioCRUD().actualizar(usuario);
        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionPresupuestos", "Gestión de Presupuestos");
    }
}
