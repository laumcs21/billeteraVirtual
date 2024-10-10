package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Billetera_virtual;
import uniquindio.edu.poo.billetera_model.Cuenta;
import uniquindio.edu.poo.billetera_model.GenerarCodigoTransaccion;
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.billetera_model.TipoTransaccion;
import uniquindio.edu.poo.billetera_model.Transaccion;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.billetera_model.BuscarUsuarioPorID;
import uniquindio.edu.poo.billetera_model.Categoria;
import uniquindio.edu.poo.billetera_model.BuscarCuenta;

public class CreacionTransaccionesController {

    @FXML
    private TextField idUsuarioField;

    @FXML
    private TextField numeroCuentaOrigenField;

    @FXML
    private TextField montoField;

    @FXML
    private TextField numeroCuentaDestinoField;

    @FXML
    private TextArea descripcionField;

    @FXML
    private ComboBox<String> categoriaComboBox;

    @FXML
    private ComboBox<TipoTransaccion> tipoTransaccionComboBox;

    @FXML
    private Label mensajeLabel;

    private Billetera_virtual billeteraVirtual;

    public CreacionTransaccionesController() {
        this.billeteraVirtual = Billetera_virtual.getInstancia();
    }

    @FXML
    public void initialize() {
        mensajeLabel.setVisible(false);
        idUsuarioField.setPromptText("ID usuario");
        numeroCuentaOrigenField.setPromptText("Numero Cuenta Origen");
        montoField.setPromptText("Monto");
        numeroCuentaDestinoField.setPromptText("Numero Cuenta Destino");
        descripcionField.setPromptText("Descripción (opcional)");
        tipoTransaccionComboBox.getItems().setAll(TipoTransaccion.values());

        List<Categoria> categorias = billeteraVirtual.getCategorias();
        for (Categoria categoria : categorias) {
            categoriaComboBox.getItems().add(categoria.getNombre());
        }

        TextField[] fields = { idUsuarioField, numeroCuentaOrigenField, montoField, numeroCuentaDestinoField };

        for (TextField field : fields) {
            field.setOnMouseClicked(event -> limpiarCampoTexto(field));
            field.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue && field.getText().isEmpty()) {
                    field.setPromptText(field.getPromptText());
                }
            });
        }

        descripcionField.setOnMouseClicked(event -> limpiarCampoTextoArea(descripcionField));

        if (!Sesion.getEsAdmin()) {
            idUsuarioField.setText(Sesion.getIdUsuario());
            idUsuarioField.setDisable(true);
        }

        tipoTransaccionComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == TipoTransaccion.TRANSFERENCIA) {
                numeroCuentaDestinoField.setVisible(true);
            } else {
                numeroCuentaDestinoField.setVisible(false);
            }
        });
    }

    private void limpiarCampoTexto(TextField campoTexto) {
        campoTexto.clear();
        campoTexto.setPromptText(campoTexto.getPromptText());
    }

    private void limpiarCampoTextoArea(TextArea campoTexto) {
        campoTexto.clear();
    }

    @FXML
    private void Crear() throws IOException {
        String idUsuario = Sesion.getEsAdmin() ? idUsuarioField.getText() : Sesion.getIdUsuario();
        String numeroCuentaOrigen = numeroCuentaOrigenField.getText();
        String numeroCuentaDestino = numeroCuentaDestinoField.isVisible() ? numeroCuentaDestinoField.getText() : null;
        String descripcion = descripcionField.getText();
        String categoriaSeleccionada = categoriaComboBox.getValue();
        double monto;
        TipoTransaccion tipo = tipoTransaccionComboBox.getValue();

        // Verifica si los campos de usuario y cuenta origen están completos
        if (idUsuario.isEmpty() || numeroCuentaOrigen.isEmpty() || montoField.getText().isEmpty()) {
            mensajeLabel.setText("Por favor, complete todos los campos obligatorios.");
            mensajeLabel.setVisible(true);
            return;
        }

        if (tipo == null) {
            mensajeLabel.setText("Por favor, seleccione un tipo de transacción.");
            mensajeLabel.setVisible(true);
            return;
        }

        // Verifica si el monto es un número válido
        try {
            monto = Double.parseDouble(montoField.getText());
        } catch (NumberFormatException e) {
            mensajeLabel.setText("Monto inválido.");
            mensajeLabel.setVisible(true);
            return;
        }

        // Verificar si la cuenta origen existe
        Optional<Cuenta> cuentaOrigenOpt = billeteraVirtual.getCuentas().stream()
                .filter(cuenta -> cuenta.getNumeroCuenta().equals(numeroCuentaOrigen))
                .findFirst();

        if (!cuentaOrigenOpt.isPresent()) {
            mensajeLabel.setText("La cuenta de origen no existe.");
            mensajeLabel.setVisible(true);
            return;
        }

        // Verificar si la cuenta pertenece al usuario
        if (!cuentaOrigenOpt.get().getIdUsuario().equals(idUsuario)) {
            mensajeLabel.setText("La cuenta de origen no pertenece al usuario.");
            mensajeLabel.setVisible(true);
            return;
        }

        // Verificar saldo suficiente
        Cuenta cuentaOrigen = BuscarCuenta.buscarCuentaPorNumero(numeroCuentaOrigen);
        if (tipo == TipoTransaccion.RETIRO || tipo == TipoTransaccion.TRANSFERENCIA) {
            if (cuentaOrigen.getSaldo() < monto) {
                mensajeLabel.setText("Saldo insuficiente para realizar la operación.");
                mensajeLabel.setVisible(true);
                return;
            }
        }

        // Si el tipo de transacción es TRANSFERENCIA, verificar la cuenta
        // destino
        if (tipo == TipoTransaccion.TRANSFERENCIA) {
            if (numeroCuentaDestino == null || numeroCuentaDestino.isEmpty()) {
                mensajeLabel.setText("Por favor, ingrese el número de cuenta destino.");
                mensajeLabel.setVisible(true);
                return;
            }

            // Verificar si la cuenta destino existe
            Optional<Cuenta> cuentaDestinoOpt = billeteraVirtual.getCuentas().stream()
                    .filter(cuenta -> cuenta.getNumeroCuenta().equals(numeroCuentaDestino))
                    .findFirst();

            if (!cuentaDestinoOpt.isPresent()) {
                mensajeLabel.setText("La cuenta de destino no existe.");
                mensajeLabel.setVisible(true);
                return;
            }
        }

        // Obtener el código de la categoría seleccionada
        Optional<Categoria> categoriaOpt = billeteraVirtual.getCategorias().stream()
                .filter(categoria -> categoria.getNombre().equals(categoriaSeleccionada))
                .findFirst();

        if (!categoriaOpt.isPresent()) {
            mensajeLabel.setText("La categoría seleccionada no es válida.");
            mensajeLabel.setVisible(true);
            return;
        }

        String codigoCategoria = categoriaOpt.get().getId();

        // Si es un administrador, verificar que el usuario exista
        if (Sesion.getEsAdmin()) {
            Optional<Usuario> usuarioOpt = billeteraVirtual.getUsuarios().stream()
                    .filter(usuario -> usuario.getId().equals(idUsuario))
                    .findFirst();

            if (!usuarioOpt.isPresent()) {
                mensajeLabel.setText("El usuario especificado no existe.");
                mensajeLabel.setVisible(true);
                return;
            }
        }
        List<Transaccion> transaccionesExistentes = billeteraVirtual.getTransacciones();
        String idTransaccionUnico = GenerarCodigoTransaccion.generarCodigoUnico(5, transaccionesExistentes);

        Transaccion nuevaTransaccion = new Transaccion.Builder(idUsuario, idTransaccionUnico, LocalDate.now(), tipo,
                monto, numeroCuentaOrigen)
                .conNumeroCuentaDestino(
                        tipo == TipoTransaccion.DEPOSITO || tipo == TipoTransaccion.TRANSFERENCIA ? numeroCuentaDestino
                                : null)
                .conDescripcion(
                        descripcion.isEmpty() || descripcion.equals("Descripción (opcional)") ? null : descripcion)
                .conCategoria(codigoCategoria)
                .build();

        // Guardar la transacción
        billeteraVirtual.getTransaccionCRUD().crear(nuevaTransaccion);
        mensajeLabel.setText("Transacción creada con éxito.");
        mensajeLabel.setVisible(true);

        if (tipo == TipoTransaccion.DEPOSITO) {
            Cuenta cuenta = BuscarCuenta.buscarCuentaPorNumero(numeroCuentaOrigen);
            double saldoNuevo = cuenta.getSaldo() + monto;
            cuenta.setSaldo(saldoNuevo);
            billeteraVirtual.getCuentaCRUD().actualizar(cuenta);

            Usuario usuario = BuscarUsuarioPorID.buscarUsuarioPorIdentificacion(idUsuario);
            usuario.actualizarSaldoTotal();
            billeteraVirtual.getUsuarioCRUD().actualizar(usuario);

        } else if (tipo == TipoTransaccion.RETIRO) {
            Cuenta cuenta = BuscarCuenta.buscarCuentaPorNumero(numeroCuentaOrigen);
            double saldoNuevo = cuenta.getSaldo() - monto;
            cuenta.setSaldo(saldoNuevo);
            billeteraVirtual.getCuentaCRUD().actualizar(cuenta);

            Usuario usuario = BuscarUsuarioPorID.buscarUsuarioPorIdentificacion(idUsuario);
            usuario.actualizarSaldoTotal();
            billeteraVirtual.getUsuarioCRUD().actualizar(usuario);
        } else {
            double saldoNuevoO = cuentaOrigen.getSaldo() - monto;
            cuentaOrigen.setSaldo(saldoNuevoO);
            billeteraVirtual.getCuentaCRUD().actualizar(cuentaOrigen);

            Cuenta cuentaDestino = BuscarCuenta.buscarCuentaPorNumero(numeroCuentaDestino);
            double saldoNuevoD = cuentaDestino.getSaldo() + monto;
            cuentaDestino.setSaldo(saldoNuevoD);
            billeteraVirtual.getCuentaCRUD().actualizar(cuentaDestino);

            Usuario usuarioOrigen = BuscarUsuarioPorID.buscarUsuarioPorIdentificacion(idUsuario);
            usuarioOrigen.actualizarSaldoTotal();
            billeteraVirtual.getUsuarioCRUD().actualizar(usuarioOrigen);

            String idUsuarioDestino = cuentaDestino.getIdUsuario();
            Usuario usuarioDestino = BuscarUsuarioPorID.buscarUsuarioPorIdentificacion(idUsuarioDestino);
            usuarioDestino.actualizarSaldoTotal();
            billeteraVirtual.getUsuarioCRUD().actualizar(usuarioDestino);

        }
    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("GestionTransacciones", "Gestión de Transacciones");
    }
}