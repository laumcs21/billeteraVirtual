package uniquindio.edu.poo.mapping.dto;

import java.io.Serializable;
import java.time.LocalDate;
import uniquindio.edu.poo.billetera_model.TipoTransaccion;

public record TransaccionDto(String idUsuario, String id, LocalDate fecha, TipoTransaccion tipo, double monto,
        String descripcion, String numeroCuentaOrigen, String numeroCuentaDestino, String idCategoria)
        implements Serializable {

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
            this.idUsuario = idUsuario;
            this.id = id;
            this.fecha = fecha;
            this.tipo = tipo;
            this.monto = monto;
            this.numeroCuentaOrigen = numeroCuentaOrigen;
        }

        public Builder conDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder conNumeroCuentaDestino(String numeroCuentaDestino) {
            this.numeroCuentaDestino = numeroCuentaDestino;
            return this;
        }

        public Builder conCategoria(String idCategoria) {
            this.idCategoria = idCategoria;
            return this;
        }

        public TransaccionDto build() {
            return new TransaccionDto(idUsuario, id, fecha, tipo, monto, descripcion, numeroCuentaOrigen,
                    numeroCuentaDestino, idCategoria);
        }
    }
}
