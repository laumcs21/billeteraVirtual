package uniquindio.edu.poo.billetera_model;

public interface CRUD<T> {
    T crear(T objeto);

    T leer(String id);

    void actualizar(T objeto);

    void eliminar(String id);
}