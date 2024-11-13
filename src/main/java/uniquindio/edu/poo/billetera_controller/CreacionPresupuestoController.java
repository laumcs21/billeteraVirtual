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
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.mapping.dto.PresupuestoDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;

public class CreacionPresupuestoController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField montoField;

    @FXML
    private ComboBox<String> categoriaComboBox;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;
    private BancoMapper bancoMapper = BancoMapper.INSTANCE;

    public CreacionPresupuestoController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        nombreField.setPromptText("Nombre del Presupuesto");
        montoField.setPromptText("Monto");

        billeteraVirtual.getCategorias().forEach(categoria -> categoriaComboBox.getItems().add(categoria.getNombre()));

        nombreField.setOnMouseClicked(event -> limpiarCampoTexto(nombreField));
        montoField.setOnMouseClicked(event -> limpiarCampoTexto(montoField));
    }

    private void limpiarCampoTexto(TextField campoTexto) {
        campoTexto.clear();
    }

    @FXML
    private void Crear() throws IOException {
        String idUsuario = Sesion.getIdUsuario();
        String nombre = nombreField.getText();
        String categoriaSeleccionada = categoriaComboBox.getValue();
        double monto;

        if (nombre.isEmpty() || montoField.getText().isEmpty() || categoriaSeleccionada == null) {
            mensajeLabel.setText("Por favor, complete todos los campos obligatorios.");
            mensajeLabel.setVisible(true);
            return;
        }

        try {
            monto = Double.parseDouble(montoField.getText());
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

        PresupuestoDto nuevoPresupuestoDto = new PresupuestoDto(idUsuario, idPresupuestoUnico, nombre, monto,
                codigoCategoria, 0.0);

        billeteraVirtual.getPresupuestoCRUD().crear(bancoMapper.presupuestoDtoToPresupuesto(nuevoPresupuestoDto));

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
