package uniquindio.edu.poo.billetera_model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GastoTabla {
    private final SimpleStringProperty tipoGasto;
    private final SimpleIntegerProperty cantidad;

    public GastoTabla(String tipoGasto, int cantidad) {
        this.tipoGasto = new SimpleStringProperty(tipoGasto);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }

    public String getTipoGasto() {
        return tipoGasto.get();
    }

    public SimpleStringProperty tipoGastoProperty() {
        return tipoGasto;
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public SimpleIntegerProperty cantidadProperty() {
        return cantidad;
    }
}

