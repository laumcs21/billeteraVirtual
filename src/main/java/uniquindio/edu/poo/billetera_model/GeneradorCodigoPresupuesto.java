package uniquindio.edu.poo.billetera_model;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class GeneradorCodigoPresupuesto implements Serializable {

    public static String generarCodigoUnico(int longitud, List<Presupuesto> presupuestosExistentes) {
        String caracteres = "abcdefghijklmnñopqrstuvwxyz0123456789";
        Random random = new Random();
        String codigo;

        do {
            StringBuilder codigoBuilder = new StringBuilder();
            for (int i = 0; i < longitud; i++) {
                int index = random.nextInt(caracteres.length());
                codigoBuilder.append(caracteres.charAt(index));
            }
            codigo = codigoBuilder.toString();
        } while (codigoExiste(codigo, presupuestosExistentes));

        return codigo;
    }

    private static boolean codigoExiste(String codigo, List<Presupuesto> presupuestosExistentes) {
        return presupuestosExistentes.stream()
                .anyMatch(presupuesto -> presupuesto.getId().equals(codigo));
    }
}
