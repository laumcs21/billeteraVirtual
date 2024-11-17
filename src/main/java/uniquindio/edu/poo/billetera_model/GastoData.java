package uniquindio.edu.poo.billetera_model;



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GastoData {
    private final SimpleStringProperty tipoGasto;
    private final SimpleIntegerProperty cantidad;

    public GastoData(String tipoGasto, int cantidad) {
        this.tipoGasto = new SimpleStringProperty(tipoGasto);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }

    public String getTipoGasto() {
        return tipoGasto.get();
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto.set(tipoGasto);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }
}

