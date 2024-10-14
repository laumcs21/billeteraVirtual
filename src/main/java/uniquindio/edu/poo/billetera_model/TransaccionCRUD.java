package uniquindio.edu.poo.billetera_model;

import java.util.List;
import java.util.Optional;

import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;
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
        ArchivoUtil.guardarRegistroLog(" Transacción Actualizada: --> " + " - Id Usuario: " + transaccion.getIdUsuario() + " - ID: " + transaccion.getId() + " - Fecha: " + transaccion.getFecha() + " - Tipo de transacción: " + transaccion.getTipo() + " - Monto: " + transaccion.getMonto() + " - Descripción: " + transaccion.getDescripcion() + " - ID Cuenta Origen: " + transaccion.getNumeroCuentaOrigen() + " - ID Cuenta Destino: " + transaccion.getNumeroCuentaDestino() + " - Categoria: " + transaccion.getNombreCategoria(), 1, " btn ActualizarTransacción ", "C:\\td\\persistencia\\log\\logCrudTransaccion.txt");
    }

    @Override
    public Transaccion crear(Transaccion transaccion) {
        if (buscarTransaccionPorId(transaccion.getId()).isPresent()) {
            throw new IllegalArgumentException("La transacción ya está registrada.");
        }
        billetera.getTransacciones().add(transaccion);
        persistencia.guardarTodasLasTransacciones(billetera.getTransacciones());
        ArchivoUtil.guardarRegistroLog(" Transacción Creada: --> " + " - Id Usuario: " + transaccion.getIdUsuario() + " - ID: " + transaccion.getId() + " - Fecha: " + transaccion.getFecha() + " - Tipo de transacción: " + transaccion.getTipo() + " - Monto: " + transaccion.getMonto() + " - Descripción: " + transaccion.getDescripcion() + " - ID Cuenta Origen: " + transaccion.getNumeroCuentaOrigen() + " - ID Cuenta Destino: " + transaccion.getNumeroCuentaDestino() + " - Categoria: " + transaccion.getNombreCategoria(), 1, " btn CrearTransacción ", "C:\\td\\persistencia\\log\\logCrudTransaccion.txt");
        return transaccion;

        
        
    }

    @Override
    public void eliminar(String id) {
        Transaccion transaccion = leer(id);
        billetera.getTransacciones().remove(transaccion);
        persistencia.guardarTodasLasTransacciones(billetera.getTransacciones());
        ArchivoUtil.guardarRegistroLog(" Transacción Eliminada: --> " + " - Id Usuario: " + transaccion.getIdUsuario() + " - ID: " + transaccion.getId() + " - Fecha: " + transaccion.getFecha() + " - Tipo de transacción: " + transaccion.getTipo() + " - Monto: " + transaccion.getMonto() + " - Descripción: " + transaccion.getDescripcion() + " - ID Cuenta Origen: " + transaccion.getNumeroCuentaOrigen() + " - ID Cuenta Destino: " + transaccion.getNumeroCuentaDestino() + " - Categoria: " + transaccion.getNombreCategoria(), 1, " btn EliminarTransacción ", "C:\\td\\persistencia\\log\\logCrudTransaccion.txt");
    }

    @Override
    public Transaccion leer(String id) {
        return buscarTransaccionPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("La transacción no está registrada."));
    }
}