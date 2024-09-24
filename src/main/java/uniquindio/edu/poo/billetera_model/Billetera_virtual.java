package uniquindio.edu.poo.billetera_model;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_usuario;

@Getter
@Setter
public class Billetera_virtual {

    private static Billetera_virtual instancia;
    private List<Usuario> usuarios;
    private List<Cuenta> cuentas;
    private List<Presupuesto> presupuesto;
    private UsuarioCRUD usuarioCRUD;

    private Billetera_virtual() {
        this.usuarios = new LinkedList<>();
        this.cuentas = new LinkedList<>();
        this.presupuesto = new LinkedList<>();
        this.usuarioCRUD = new UsuarioCRUD(this);
        cargarDatos();
    }

    public static Billetera_virtual getInstancia() {
        if (instancia == null) {
            synchronized (Billetera_virtual.class) {
                if (instancia == null) {
                    instancia = new Billetera_virtual();
                }
            }
        }
        return instancia;
    }

    private void cargarDatos() {
        Persistencia_usuario persistencia = Persistencia_usuario.getInstancia();
        List<Usuario> usuariosCargados = persistencia.cargarUsuarios();
        if (usuariosCargados != null) {
            this.usuarios.addAll(usuariosCargados);
        }
    }

    public static void main(String[] args) {
        Billetera_virtual ej = Billetera_virtual.getInstancia();
        // Imprimir usuarios para verificar
        for (Usuario usuario : ej.getUsuarios()) {
            System.out.println(usuario); // Asegúrate de que Usuario tenga un método toString()
        }
    }
}