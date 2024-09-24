package uniquindio.edu.poo.billetera_persistencia;

import java.util.List;

import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.billetera_archivo_util.archivoUtilUsuario;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Persistencia_usuario {
    private static final String RUTA_ARCHIVO = "src/data/usuarios.txt";
    private static Persistencia_usuario instancia;

    public static Persistencia_usuario getInstancia() {
        if (instancia == null) {
            synchronized (Persistencia_usuario.class) {
                if (instancia == null) {
                    instancia = new Persistencia_usuario();
                }
            }
        }
        return instancia;
    }

    public void guardarTodosLosUsuarios(List<Usuario> usuarios) {
        StringBuilder textoUsuario = new StringBuilder();

        for (Usuario usuario : usuarios) {
            textoUsuario.append("Nombre: ").append(usuario.getNombre()).append("\n");
            textoUsuario.append("ID: ").append(usuario.getId()).append("\n");
            textoUsuario.append("Correo: ").append(usuario.getCorreo()).append("\n");
            textoUsuario.append("Teléfono: ").append(usuario.getTelefono()).append("\n");
            textoUsuario.append("Dirección: ").append(usuario.getDireccion()).append("\n");
            textoUsuario.append("Saldo Total: ").append(usuario.getSaldoTotal()).append("\n");
            textoUsuario.append("Contraseña: ").append(usuario.getContraseña()).append("\n");
            textoUsuario.append("\n");
        }

        try {
            archivoUtilUsuario.guardarTexto(textoUsuario.toString());
        } catch (IOException e) {
            System.err.println("Error al guardar los usuarios: " + e.getMessage());
        }
    }

    public List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            String nombre = null, id = null, correo = null, telefono = null, direccion = null, contraseña = null;
            double saldoTotal = 0.0;

            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }

                if (linea.startsWith("Nombre: ")) {
                    nombre = linea.substring("Nombre: ".length());
                } else if (linea.startsWith("ID: ")) {
                    id = linea.substring("ID: ".length());
                } else if (linea.startsWith("Correo: ")) {
                    correo = linea.substring("Correo: ".length());
                } else if (linea.startsWith("Teléfono: ")) {
                    telefono = linea.substring("Teléfono: ".length());
                } else if (linea.startsWith("Dirección: ")) {
                    direccion = linea.substring("Dirección: ".length());
                } else if (linea.startsWith("Contraseña: ")) {
                    contraseña = linea.substring("Contraseña: ".length());
                } else if (linea.startsWith("Saldo Total: ")) {
                    try {
                        saldoTotal = Double.parseDouble(linea.substring("Saldo Total: ".length()));
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir el saldo total: " + linea);
                    }
                }

                if (nombre != null && contraseña != null && id != null && correo != null && telefono != null
                        && direccion != null) {
                    Usuario usuario = new Usuario(id, contraseña, nombre, correo, telefono, direccion, saldoTotal);
                    usuarios.add(usuario);

                    nombre = null;
                    id = null;
                    correo = null;
                    telefono = null;
                    direccion = null;
                    contraseña = null;
                    saldoTotal = 0.0;
                }
            }

        } catch (IOException e) {
            System.err.println("Error al cargar los usuarios desde el archivo: " + e.getMessage());
        }

        return usuarios;
    }

}
