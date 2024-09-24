package uniquindio.edu.poo.billetera_model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Transaccion {

    private Usuario usuario;
    private String id;
    private LocalDate fecha;
    private double monto;
    private String descripcion;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private Categoria categoria;

    private Transaccion(Builder builder) {
        this.usuario = builder.usuario;
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.monto = builder.monto;
        this.descripcion = builder.descripcion;
        this.cuentaOrigen = builder.cuentaOrigen;
        this.cuentaDestino = builder.cuentaDestino;
        this.categoria = builder.categoria;
    }

    public static class Builder {

        private Usuario usuario;
        private String id;
        private LocalDate fecha;
        private double monto;
        private String descripcion;
        private Cuenta cuentaOrigen;
        private Cuenta cuentaDestino;
        private Categoria categoria;

        public Builder(Usuario usuario, String id, LocalDate fecha, double monto, Cuenta cuentaOrigen,
                Cuenta cuentaDestino) {
            this.usuario = usuario;
            this.id = id;
            this.fecha = fecha;
            this.monto = monto;
            this.cuentaOrigen = cuentaOrigen;
            this.cuentaDestino = cuentaDestino;
        }

        public Builder conDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder conCategoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public Transaccion build() {
            return new Transaccion(this);
        }
    }
}
