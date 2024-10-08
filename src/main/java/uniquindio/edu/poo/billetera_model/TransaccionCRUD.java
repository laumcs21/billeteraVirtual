package uniquindio.edu.poo.billetera_model;

import java.util.List;
import java.util.Optional;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_Transaccion;

public class TransaccionCRUD implements CRUD<Transaccion> {

    private Billetera_virtual billetera;
    private Persistencia_Transaccion persistencia = new Persistencia_Transaccion();

    public TransaccionCRUD(Billetera_virtual billetera) {
        this.billetera = billetera;
    }

    public Optional<Transaccion> buscarTransaccionPorId(String id) {
        return buscarTransaccionRecursivo(billetera.getTransacciones(), id, 0);
    }

    private Optional<Transaccion> buscarTransaccionRecursivo(List<Transaccion> transacciones, String id, int indice) {
        if (indice >= transacciones.size()) {
            return Optional.empty();
        }

        Transaccion transaccion = transacciones.get(indice);
        if (transaccion.getId().equals(id)) {
            return Optional.of(transaccion);
        }

        return buscarTransaccionRecursivo(transacciones, id, indice + 1);
    }

    @Override
    public void actualizar(Transaccion transaccion) {
        eliminar(transaccion.getId());
        billetera.getTransacciones().add(transaccion);
        persistencia.guardarTodasLasTransacciones(billetera.getTransacciones());
    }

    @Override
    public Transaccion crear(Transaccion transaccion) {
        if (buscarTransaccionPorId(transaccion.getId()).isPresent()) {
            throw new IllegalArgumentException("La transacción ya está registrada.");
        }
        billetera.getTransacciones().add(transaccion);
        persistencia.guardarTodasLasTransacciones(billetera.getTransacciones());
        return transaccion;
    }

    @Override
    public void eliminar(String id) {
        Transaccion transaccion = leer(id);
        billetera.getTransacciones().remove(transaccion);
        persistencia.guardarTodasLasTransacciones(billetera.getTransacciones());
    }

    @Override
    public Transaccion leer(String id) {
        return buscarTransaccionPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("La transacción no está registrada."));
    }
}