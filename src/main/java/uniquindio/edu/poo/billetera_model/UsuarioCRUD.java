package uniquindio.edu.poo.billetera_model;

import java.util.Optional;

import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;
import uniquindio.edu.poo.billetera_exception.CorreoElectronicoException;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_usuario;
import java.util.List;

public class UsuarioCRUD implements CRUD<Usuario> {

    private Billetera_virtual billetera;
    private Persistencia_usuario persistencia = new Persistencia_usuario();

    public UsuarioCRUD(Billetera_virtual billetera) {
        this.billetera = billetera;
    }

    public Optional<Usuario> buscarUsuarioPorIdentificacion(String identificacion) {
        return buscarUsuarioRecursivo(billetera.getUsuarios(), identificacion, 0);
    }

    private Optional<Usuario> buscarUsuarioRecursivo(List<Usuario> usuarios, String identificacion, int indice) {
        if (indice >= usuarios.size()) {
            return Optional.empty();
        }

        Usuario usuario = usuarios.get(indice);
        if (usuario.getId().equals(identificacion)) {
            return Optional.of(usuario);
        }

        return buscarUsuarioRecursivo(usuarios, identificacion, indice + 1);
    }

    public static boolean buscarCadena(String frase, String busqueda) {
        return frase.contains(busqueda);
    }

    @Override
    public void actualizar(Usuario usuario) {
        eliminar(usuario.getId());
        billetera.getUsuarios().add(usuario);
        persistencia.guardarTodosLosUsuarios(billetera.getUsuarios());
        ArchivoUtil.guardarRegistroLog(" Usuario Actualizado--> "+ " ID:" + usuario.getId()+ " - Nombre: " + usuario.getNombre() + " - Correo: "+ usuario.getCorreo() + " - Telefono: "+ usuario.getTelefono() + " - Dirección: "+ usuario.getDireccion() + " - Saldo Total: "+ usuario.getSaldoTotal(), 1, " btnActualizarUsuarios", "C:\\td\\persistencia\\log\\logCrudUsuario.txt");
    }

    @Override
    public Usuario crear(Usuario usuario) throws CorreoElectronicoException {
        if (buscarUsuarioPorIdentificacion(usuario.getId()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya está registrado.");
        }

        if (!buscarCadena(usuario.getCorreo(), "@") || !buscarCadena(usuario.getCorreo(), ".com")) {
            throw new CorreoElectronicoException("El correo no es válido");
        }

        billetera.getUsuarios().add(usuario);
        persistencia.guardarTodosLosUsuarios(billetera.getUsuarios());
        ArchivoUtil.guardarRegistroLog(" Usuario Registrado--> "+ " ID:" + usuario.getId()+ " - Nombre: " + usuario.getNombre() + " - Correo: "+ usuario.getCorreo() + " - Telefono: "+ usuario.getTelefono() + " - Dirección: "+ usuario.getDireccion() + " - Saldo Total: "+ usuario.getSaldoTotal(), 1, " btnCrearUsuarios", "C:\\td\\persistencia\\log\\logCrudUsuario.txt");

        return usuario;
    }

    @Override
    public void eliminar(String identificacion) {
        Usuario usuario = leer(identificacion);
        billetera.getUsuarios().remove(usuario);
        ArchivoUtil.guardarRegistroLog(" Usuario Eliminado--> "+ " ID:" + usuario.getId()+ " - Nombre: " + usuario.getNombre() + " - Correo: "+ usuario.getCorreo() + " - Telefono: "+ usuario.getTelefono() + " - Dirección: "+ usuario.getDireccion() + " - Saldo Total: "+ usuario.getSaldoTotal(), 1, " btnEliminarUsuarios", "C:\\td\\persistencia\\log\\logCrudUsuario.txt");
        persistencia.guardarTodosLosUsuarios(billetera.getUsuarios());
        
        
    }

    @Override
    public Usuario leer(String identificacion) {
        return buscarUsuarioPorIdentificacion(identificacion)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no está registrado."));
               
    }
}
