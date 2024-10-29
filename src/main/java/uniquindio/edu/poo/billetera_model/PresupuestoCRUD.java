package uniquindio.edu.poo.billetera_model;

import java.util.Optional;
import java.util.List;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_Presupuesto;

public class PresupuestoCRUD implements CRUD<Presupuesto> {

    private Billetera_virtual billetera;
    private Persistencia_Presupuesto persistencia = new Persistencia_Presupuesto();

    public PresupuestoCRUD(Billetera_virtual billetera) {
        this.billetera = billetera;
    }

    public Optional<Presupuesto> buscarPresupuestoPorIdentificacion(String id) {
        return buscarPresupuestoRecursivo(billetera.getPresupuestos(), id, 0);
    }

    private Optional<Presupuesto> buscarPresupuestoRecursivo(List<Presupuesto> presupuestos, String id, int indice) {
        if (indice >= presupuestos.size()) {
            return Optional.empty();
        }

        Presupuesto presupuesto = presupuestos.get(indice);
        if (presupuesto.getId().equals(id)) {
            return Optional.of(presupuesto);
        }

        return buscarPresupuestoRecursivo(presupuestos, id, indice + 1);
    }

    @Override
    public void actualizar(Presupuesto presupuesto) {
        eliminar(presupuesto.getId());
        billetera.getPresupuestos().add(presupuesto);
        persistencia.guardarTodosLosPresupuestos(billetera.getPresupuestos());
    }

    @Override
    public Presupuesto crear(Presupuesto presupuesto) {
        if (buscarPresupuestoPorIdentificacion(presupuesto.getId()).isPresent()) {
            throw new IllegalArgumentException("El presupuesto ya está registrado.");
        }
        billetera.getPresupuestos().add(presupuesto);
        persistencia.guardarTodosLosPresupuestos(billetera.getPresupuestos());

        return presupuesto;
    }

    @Override
    public void eliminar(String id) {
        Presupuesto presupuesto = leer(id);
        billetera.getPresupuestos().remove(presupuesto);
        persistencia.guardarTodosLosPresupuestos(billetera.getPresupuestos());
    }

    @Override
    public Presupuesto leer(String id) {
        return buscarPresupuestoPorIdentificacion(id)
                .orElseThrow(() -> new IllegalArgumentException("El presupuesto no está registrado."));
    }
}
