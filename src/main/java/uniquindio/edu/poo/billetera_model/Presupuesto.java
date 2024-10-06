package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Presupuesto {

    private Usuario usuario;
    private String id;
    private String nombre;
    private double monto;
    private Categoria categoria;

    private Presupuesto(Builder builder) {
        this.usuario = builder.usuario;
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.monto = builder.monto;
        this.categoria = builder.categoria;

    }

    public static class Builder {
        private Usuario usuario;
        private String id;
        private String nombre;
        private double monto;
        private Categoria categoria;

        public Builder(Usuario usuario, String id, String nombre, double monto) {
            this.usuario = usuario;
            this.id = id;
            this.nombre = nombre;
            this.monto = monto;
        }

        public Builder conCategoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public Presupuesto build() {
            return new Presupuesto(this);
        }
    }

}
