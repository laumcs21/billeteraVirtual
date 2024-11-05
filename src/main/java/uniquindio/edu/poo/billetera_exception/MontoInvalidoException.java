package uniquindio.edu.poo.billetera_exception;

public class MontoInvalidoException extends RuntimeException {
    public MontoInvalidoException(String mensaje) {
        super(mensaje);
    }
}