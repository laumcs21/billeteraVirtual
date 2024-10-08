package uniquindio.edu.poo.billetera_model;

import java.util.List;
import java.util.Random;

public class GenerarCodigoTransaccion {

    public static String generarCodigoUnico(int longitud, List<Transaccion> transaccionesExistentes) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        String codigo;

        do {
            StringBuilder codigoBuilder = new StringBuilder();
            for (int i = 0; i < longitud; i++) {
                int index = random.nextInt(caracteres.length());
                codigoBuilder.append(caracteres.charAt(index));
            }
            codigo = codigoBuilder.toString();
        } while (codigoExiste(codigo, transaccionesExistentes));

        return codigo;
    }

    private static boolean codigoExiste(String codigo, List<Transaccion> transaccionesExistentes) {
        return transaccionesExistentes.stream()
                .anyMatch(transaccion -> transaccion.getId().equals(codigo));
    }
}
