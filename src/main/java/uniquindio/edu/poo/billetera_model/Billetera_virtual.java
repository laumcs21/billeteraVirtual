package uniquindio.edu.poo.billetera_model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_usuario;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_Cuenta;

@Getter
@Setter
public class Billetera_virtual {

    private static Billetera_virtual instancia;
    private List<Usuario> usuarios;
    private List<Cuenta> cuentas;
    private List<Presupuesto> presupuesto;
    private UsuarioCRUD usuarioCRUD;
    private CuentaCRUD cuentaCRUD;

    private Billetera_virtual() {
        this.usuarios = new LinkedList<>();
        this.cuentas = new LinkedList<>();
        this.presupuesto = new LinkedList<>();
        this.usuarioCRUD = new UsuarioCRUD(this);
        this.cuentaCRUD = new CuentaCRUD(this);
    }

    public static Billetera_virtual getInstancia() {
        if (instancia == null) {
            synchronized (Billetera_virtual.class) {
                if (instancia == null) {
                    instancia = new Billetera_virtual();
                    instancia.cargarDatosUsuarios();
                    instancia.cargarDatosCuentas();
                }
            }
        }
        return instancia;
    }

    private void cargarDatosUsuarios() {
        Persistencia_usuario persistencia = Persistencia_usuario.getInstancia();
        try {
            List<Usuario> usuariosCargados = persistencia.cargarUsuarios();
            if (usuariosCargados != null) {
                this.usuarios.addAll(usuariosCargados);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar los usuarios desde el archivo: " + e.getMessage());
        }
    }

    private void cargarDatosCuentas() {
        Persistencia_Cuenta persistencia = Persistencia_Cuenta.getInstancia();
        try {
            List<Cuenta> cuentasCargadas = persistencia.cargarCuentas();
            if (cuentasCargadas != null) {
                this.cuentas.addAll(cuentasCargadas);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las cuentas desde el archivo: " + e.getMessage());
        }
    }

}
