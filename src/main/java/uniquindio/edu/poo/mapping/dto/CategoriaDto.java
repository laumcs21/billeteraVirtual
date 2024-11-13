package uniquindio.edu.poo.mapping.dto;

import java.io.Serializable;

public record CategoriaDto(String id, String nombre, String descripcion) implements Serializable {

    public static class Builder {
        private String id;
        private String nombre;
        private String descripcion; // Opcional

        public Builder(String id, String nombre) { // Los campos obligatorios en el constructor
            this.id = id;
            this.nombre = nombre;
        }

        public Builder conDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public CategoriaDto build() {
            return new CategoriaDto(id, nombre, descripcion);
        }
    }
}
