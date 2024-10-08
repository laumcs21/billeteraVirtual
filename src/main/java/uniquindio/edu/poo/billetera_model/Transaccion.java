package uniquindio.edu.poo.billetera_model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Transaccion {

    private String idUsuario;
    private String id;
    private LocalDate fecha;
    private TipoTransaccion tipo;
    private double monto;
    private String descripcion;
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
    private String idCategoria;

    private Transaccion(Builder builder) {
        this.idUsuario = builder.idUsuario;
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.tipo = builder.tipo;
        this.monto = builder.monto;
        this.descripcion = builder.descripcion;
        this.numeroCuentaOrigen = builder.numeroCuentaOrigen;
        this.numeroCuentaDestino = builder.numeroCuentaDestino;
        this.idCategoria = builder.idCategoria;
    }

    public static class Builder {

        private String idUsuario;
        private String id;
        private LocalDate fecha;
        private TipoTransaccion tipo;
        private double monto;
        private String descripcion;
        private String numeroCuentaOrigen;
        private String numeroCuentaDestino;
        private String idCategoria;

        public Builder(String idUsuario, String id, LocalDate fecha, TipoTransaccion tipo, double monto,
                String numeroCuentaOrigen) {
            this.tipo = tipo;
            this.idUsuario = idUsuario;
            this.id = id;
            this.fecha = fecha;
            this.monto = monto;
            this.numeroCuentaOrigen = numeroCuentaOrigen;
        }

        public Builder conNumeroCuentaDestino(String numeroCuentaDestino) {
            this.numeroCuentaDestino = numeroCuentaDestino;
            return this;
        }

        public Builder conDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder conCategoria(String idCategoria) {
            this.idCategoria = idCategoria;
            return this;
        }

        public Transaccion build() {
            return new Transaccion(this);
        }
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "usuario='" + idUsuario + '\'' +
                ", codigoTransaccion='" + id + '\'' +
                ", fecha=" + fecha +
                ", tipoTransaccion=" + tipo +
                ", monto=" + monto +
                ", numeroCuentaOrigen='" + numeroCuentaOrigen + '\'' +
                (numeroCuentaDestino != null ? ", numeroCuentaDestino='" + numeroCuentaDestino + '\'' : "") +
                (descripcion != null ? ", descripcion='" + descripcion + '\'' : "") +
                (idCategoria != null ? ", categoria='" + idCategoria + '\'' : "") +
                '}';
    }
}
