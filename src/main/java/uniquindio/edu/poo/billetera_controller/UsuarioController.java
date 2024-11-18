package uniquindio.edu.poo.billetera_controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import uniquindio.edu.poo.billetera_app.App;
import uniquindio.edu.poo.billetera_model.Sesion;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.mapping.dto.UsuarioDto;
import uniquindio.edu.poo.mapping.mappers.BancoMapper;
import uniquindio.edu.poo.billetera_model.BuscarUsuarioPorID;

public class UsuarioController {

    private String idUsuario = Sesion.getIdUsuario();
    private BancoMapper bancoMapper = BancoMapper.INSTANCE;

    @FXML
    private Button calificacionLabel;

    @FXML
    private TextArea saldoField;

    @FXML
    public void initialize() {

        Usuario usuario = BuscarUsuarioPorID.buscarUsuarioPorIdentificacion(idUsuario);

        if (usuario != null) {
            UsuarioDto usuarioDto = bancoMapper.usuarioToUsuarioDto(usuario);
            double saldo = usuarioDto.saldoTotal();
            saldoField.setText(String.format("%.2f", saldo));
        } else {
            saldoField.setText("Usuario no encontrado");
        }
    }

    @FXML
    private void Actualizar() throws IOException {
        App.setRoot("ActualizacionUsuario", "Actualización Usuario");
    }

    @FXML
    private void Transacciones() throws IOException {
        App.setRoot("GestionTransacciones", "Gestión transacciones");
    }

    @FXML
    private void Cuentas() throws IOException {
        App.setRoot("BusquedaCuentasUsuario", "Busqueda Cuentas Usuario");
    }

    @FXML
    private void Presupuesto() throws IOException {
        App.setRoot("GestionPresupuestos", "Gestion Presupuestos");
    }

    @FXML
    void Calificar() throws IOException {
        App.setRoot("Calificacion", "Calificacion");

    }

    @FXML
    private void Volver() throws IOException {
        App.setRoot("InicioSesion", "Inicio Sesion");
    }
}
