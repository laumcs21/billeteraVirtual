package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Categoria {
    public String id;
    public String nombre;
    public String descripcion;

    public Categoria(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.descripcion = builder.descripcion;
    }

    public static class Builder {
        public String id;
        public String nombre;
        public String descripcion;

        public Builder(String id, String nombre) {
            this.id = id;
            this.nombre = nombre;

        }

        public Builder conDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Categoria build() {
            return new Categoria(this);
        }
    }
}
