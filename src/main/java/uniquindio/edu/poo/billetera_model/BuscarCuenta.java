package uniquindio.edu.poo.billetera_model;

import java.util.List;

public class BuscarCuenta {

    private static Billetera_virtual billeteraVirtual;

    static {
        billeteraVirtual = Billetera_virtual.getInstancia();
    }

    public static Cuenta buscarCuentaPorNumero(String numero) {
        return buscarCuentaPorNumeroRecursivo(billeteraVirtual.getCuentas(), numero, 0);
    }

    private static Cuenta buscarCuentaPorNumeroRecursivo(List<Cuenta> cuentas, String numero, int indice) {
        if (indice >= cuentas.size()) {
            return null;
        }

        Cuenta cuenta = cuentas.get(indice);
        if (cuenta.getNumeroCuenta().equals(numero)) {
            return cuenta;
        }

        return buscarCuentaPorNumeroRecursivo(cuentas, numero, indice + 1);
    }

}
