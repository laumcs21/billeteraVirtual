package uniquindio.edu.poo.billetera_model;

import java.util.List;

public class BuscarPresupuesto {

    private static Billetera_virtual billeteraVirtual;

    static {
        billeteraVirtual = Billetera_virtual.getInstancia();
    }

    public static Presupuesto buscarPresupuestoPorID(String id) {
        return buscarPresupuestoPorID(billeteraVirtual.getPresupuestos(), id, 0);
    }

    private static Presupuesto buscarPresupuestoPorID(List<Presupuesto> presupuestos, String id, int indice) {
        if (indice >= presupuestos.size()) {
            return null;
        }

        Presupuesto presupuesto = presupuestos.get(indice);
        if (presupuesto.getId().equals(id)) {
            return presupuesto;
        }

        return buscarPresupuestoPorID(presupuestos, id, indice + 1);
    }

}
