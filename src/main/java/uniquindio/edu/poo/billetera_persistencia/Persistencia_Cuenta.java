package uniquindio.edu.poo.billetera_persistencia;

<<<<<<< HEAD
import java.util.List;

import uniquindio.edu.poo.billetera_model.Cuenta;
import uniquindio.edu.poo.billetera_model.TipoCuenta;
import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia_Cuenta {
    private static final String RUTA_ARCHIVO = "C:\\td\\persistencia\\archivos\\cuentas.txt";
    private static Persistencia_Cuenta instancia;

    public static Persistencia_Cuenta getInstancia() {
        if (instancia == null) {
            synchronized (Persistencia_Cuenta.class) {
                if (instancia == null) {
                    instancia = new Persistencia_Cuenta();
                }
            }
        }
        return instancia;
    }

    public void guardarTodasLasCuentas(List<Cuenta> cuentas) {

        StringBuilder textoCuenta = new StringBuilder();

        for (Cuenta cuenta : cuentas) {
            textoCuenta.append(cuenta.getIdUsuario()).append("@@");
            textoCuenta.append(cuenta.getId()).append("@@");
            textoCuenta.append(cuenta.getNombreBanco()).append("@@");
            textoCuenta.append(cuenta.getNumeroCuenta()).append("@@");
            textoCuenta.append(cuenta.getTipoCuenta()).append("\n");
        }
        try {
            ArchivoUtil.guardarArchivo(RUTA_ARCHIVO, textoCuenta.toString(), false);
        } catch (IOException e) {
            System.err.println("Error al guardar las cuentas: " + e.getMessage());
        }
    }

    public List<Cuenta> cargarCuentas() throws IOException {
        List<Cuenta> cuentas = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las cuentas");
        }

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO);

        try {
            for (String cuentasTexto : contenido) {
                String[] split = cuentasTexto.split("@@");

                String idUsuario = split[0];

                TipoCuenta tipoCuenta = TipoCuenta.valueOf(split[split.length - 1]);

                Cuenta cuenta = new Cuenta(idUsuario, split[1], split[2], split[3], tipoCuenta);
                cuentas.add(cuenta);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar las cuentas desde el archivo: " + e.getMessage());
        }
        return cuentas;
    }
=======
public class Persistencia_Cuenta {

>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
}
