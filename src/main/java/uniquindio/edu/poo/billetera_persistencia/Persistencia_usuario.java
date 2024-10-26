package uniquindio.edu.poo.billetera_persistencia;

import java.util.List;


import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia_usuario {
    private static final String RUTA_ARCHIVO = "C:\\td\\persistencia\\archivos\\usuarios.txt";
    private static final String RUTA_ARCHIVOXML = "C:\\td\\persistencia\\usuario.data";
    private static final String RUTA_ARCHIVOBIN = "C:\\td\\persistencia\\usuarioBinario.data\\";
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
            textoUsuario.append(usuario.getId()).append("@@");
            textoUsuario.append(usuario.getContraseña()).append("@@");
            textoUsuario.append(usuario.getNombre()).append("@@");
            textoUsuario.append(usuario.getCorreo()).append("@@");
            textoUsuario.append(usuario.getTelefono()).append("@@");
            textoUsuario.append(usuario.getDireccion()).append("@@");
            textoUsuario.append(usuario.getSaldoTotal()).append("\n");

            
        }

        try {
            ArchivoUtil.guardarArchivo(RUTA_ARCHIVO, textoUsuario.toString(), false);
        } catch (IOException e) {
            System.err.println("Error al guardar los usuarios: " + e.getMessage());
        }
       
        
    }

    public List<Usuario> cargarUsuarios() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO);

        try {
            for (String usuarioTexto : contenido) {
                String[] split = usuarioTexto.split("@@");
                Usuario usuario = new Usuario(split[0], split[1], split[2], split[3], split[4], split[5],
                        Double.valueOf(split[6]));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar los usuarios desde el archivo: " + e.getMessage());
        }
        return usuarios;
    }
     public List<Usuario> cargarUsuariosXML() {
        try {
            return (List<Usuario>) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVOXML);
        } catch (IOException e) {
            System.err.println("Error al cargar los usuarios desde el archivo XML: " + e.getMessage());
            return new ArrayList<>();
        } catch (ClassCastException e) {
            System.err.println(
                    "Error de conversión al cargar los usuarios desde el archivo XML: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void guardarUsuariosEnXML(Usuario usuario) {
        try {
            List<Usuario > usuarios = cargarUsuariosXML();

            if (usuarios == null) {
                usuarios = new ArrayList<>();
            }

            usuarios.add(usuario);

            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVOXML, usuarios);
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public void guardarUsuariosBinario(Usuario usuario) {
        try {


  
            List<Usuario> usuarios = cargarUsuariosBinario();

            if (usuarios == null) {
                usuarios = new ArrayList<>();
            }

            usuarios.add(usuario);

            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVOBIN, usuarios);
            System.out.println("Registro guardado en binario: " + usuario);

        } catch (Exception e) {
            System.out.println("Error al guardar los usuarios en binario: " + e.getMessage());
        }
    }

    private List<Usuario> cargarUsuariosBinario() {
        try {
            return (List<Usuario >) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVOBIN);
        } catch (Exception e) {
            System.out.println("Error al cargar los usuarios desde binario: " + e.getMessage());
            return null;
        }
    }
}



