package uniquindio.edu.poo.billetera_model;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class GeneradorNumeroCuenta implements Serializable {

    public static String generarNumeroUnico(int longitud, List<Cuenta> cuentasExistentes) {
        String caracteres = "0123456789";
        Random random = new Random();
        String codigo;

        do {
            StringBuilder codigoBuilder = new StringBuilder();
            for (int i = 0; i < longitud; i++) {
                int index = random.nextInt(caracteres.length());
                codigoBuilder.append(caracteres.charAt(index));
            }
            codigo = codigoBuilder.toString();
        } while (codigoExiste(codigo, cuentasExistentes));

        return codigo;
    }

    private static boolean codigoExiste(String codigo, List<Cuenta> cuentasExistentes) {
        return cuentasExistentes.stream()
                .anyMatch(cuenta -> cuenta.getNumeroCuenta().equals(codigo));
    }
}
