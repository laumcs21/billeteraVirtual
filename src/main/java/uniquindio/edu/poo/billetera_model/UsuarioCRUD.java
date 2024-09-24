package uniquindio.edu.poo.billetera_model;

import java.util.Optional;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_usuario;

public class UsuarioCRUD implements CRUD<Usuario> {

    private Billetera_virtual billetera;
    private Persistencia_usuario persistencia = new Persistencia_usuario();

    public UsuarioCRUD(Billetera_virtual billetera) {
        this.billetera = billetera;
    }

    public Optional<Usuario> buscarUsuarioPorIdentificacion(String identificacion) {
        return billetera.getUsuarios().stream()
                .filter(usuario -> usuario.getId().equals(identificacion))
                .findAny();
    }

    @Override
    public void actualizar(Usuario usuario) {
        eliminar(usuario.getId());
        billetera.getUsuarios().add(usuario);
        persistencia.guardarTodosLosUsuarios(billetera.getUsuarios());
    }

    @Override
    public Usuario crear(Usuario usuario) {
        if (buscarUsuarioPorIdentificacion(usuario.getId()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya está registrado.");
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
