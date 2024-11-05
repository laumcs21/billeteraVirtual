package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Presupuesto {

    private String idUsuario;
    private String id;
    private String nombre;
    private double monto;
    private String idCategoria;
    private double montoGastado;

    public Presupuesto(String idUsuario, String id, String nombre, double monto, String idCategoria) {
        this.idUsuario = idUsuario;
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
        this.idCategoria = idCategoria;
        this.montoGastado = 0;
    }

}
