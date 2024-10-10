package uniquindio.edu.poo.billetera_model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_usuario;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_Cuenta;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_Transaccion;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_Categoria;

@Getter
@Setter
public class Billetera_virtual {

    private static Billetera_virtual instancia;
    private List<Usuario> usuarios;
    private List<Cuenta> cuentas;
    private List<Transaccion> transacciones;
    private List<Categoria> categorias;
    private List<Presupuesto> presupuesto;
    private UsuarioCRUD usuarioCRUD;
    private CuentaCRUD cuentaCRUD;
    private TransaccionCRUD transaccionCRUD;
    private CategoriaCRUD categoriaCRUD;

    private Billetera_virtual() {
        this.usuarios = new LinkedList<>();
        this.cuentas = new LinkedList<>();
        this.transacciones = new LinkedList<>();
        this.presupuesto = new LinkedList<>();
        this.categorias = new LinkedList<>();
        this.usuarioCRUD = new UsuarioCRUD(this);
        this.cuentaCRUD = new CuentaCRUD(this);
        this.transaccionCRUD = new TransaccionCRUD(this);
        this.categoriaCRUD = new CategoriaCRUD(this);
    }

    public static Billetera_virtual getInstancia() {
        if (instancia == null) {
            synchronized (Billetera_virtual.class) {
                if (instancia == null) {
                    instancia = new Billetera_virtual();
                    instancia.cargarDatosUsuarios();
                    instancia.cargarDatosCuentas();
                    instancia.cargarDatosTransacciones();
                    instancia.cargarDatosCategorias();
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

    private void cargarDatosTransacciones() {
        Persistencia_Transaccion persistencia = Persistencia_Transaccion.getInstancia();
        try {
            this.transacciones.clear();
            List<Transaccion> transaccionesCargadas = persistencia.cargarTransacciones();
            if (transaccionesCargadas != null) {
                this.transacciones.addAll(transaccionesCargadas);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las cuentas desde el archivo: " + e.getMessage());
        }
    }

    private void cargarDatosCategorias() {
        Persistencia_Categoria persistencia = Persistencia_Categoria.getInstancia();
        try {
            // this.categorias.clear();
            List<Categoria> categoriasCargadas = persistencia.cargarCategorias();
            if (categoriasCargadas != null) {
                this.categorias.addAll(categoriasCargadas);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las cuentas desde el archivo: " + e.getMessage());
        }
    }
}
