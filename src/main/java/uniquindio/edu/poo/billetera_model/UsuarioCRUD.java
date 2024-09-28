package uniquindio.edu.poo.billetera_model;

import java.util.Optional;

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

        return usuario;
    }

    @Override
    public void eliminar(String identificacion) {
        Usuario usuario = leer(identificacion);
        billetera.getUsuarios().remove(usuario);
        persistencia.guardarTodosLosUsuarios(billetera.getUsuarios());
    }

    @Override
    public Usuario leer(String identificacion) {
        return buscarUsuarioPorIdentificacion(identificacion)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no está registrado."));
    }
}
