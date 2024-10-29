package uniquindio.edu.poo.billetera_persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uniquindio.edu.poo.billetera_model.Presupuesto;
import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;

public class Persistencia_Presupuesto {

    private static final String RUTA_ARCHIVO = "C:\\td\\persistencia\\archivos\\presupuestos.txt";
    private static Persistencia_Presupuesto instancia;

    public static Persistencia_Presupuesto getInstancia() {
        if (instancia == null) {
            synchronized (Persistencia_Presupuesto.class) {
                if (instancia == null) {
                    instancia = new Persistencia_Presupuesto();
                }
            }
        }
        return instancia;
    }

    public void guardarTodosLosPresupuestos(List<Presupuesto> presupuestos) {
        StringBuilder textoPresupuesto = new StringBuilder();

        for (Presupuesto presupuesto : presupuestos) {
            textoPresupuesto.append(presupuesto.getIdUsuario()).append("@@");
            textoPresupuesto.append(presupuesto.getId()).append("@@");
            textoPresupuesto.append(presupuesto.getNombre()).append("@@");
            textoPresupuesto.append(presupuesto.getMonto()).append("@@");
            textoPresupuesto.append(presupuesto.getIdCategoria()).append("@@");
            textoPresupuesto.append(presupuesto.getMontoGastado()).append("\n");
        }

        try {
            ArchivoUtil.guardarArchivo(RUTA_ARCHIVO, textoPresupuesto.toString(), false);
        } catch (IOException e) {
            System.err.println("Error al guardar los presupuestos: " + e.getMessage());
        }
    }

    public List<Presupuesto> cargarPresupuestos() throws IOException {
        List<Presupuesto> presupuestos = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo de presupuestos");
        }

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO);

        for (String presupuestoTexto : contenido) {
            String[] split = presupuestoTexto.split("@@");
            if (split.length < 6) {
                System.err.println("Línea inválida o incompleta: " + presupuestoTexto);
                continue;
            }

            try {
                Presupuesto presupuesto = new Presupuesto(
                        split[0],
                        split[1],
                        split[2],
                        Double.parseDouble(split[3]),
                        split[4]);

                presupuesto.setMontoGastado(split[5]);

                presupuestos.add(presupuesto);

            } catch (Exception e) {
                System.err.println("Error al procesar la línea: " + presupuestoTexto);
            }
        }

        return presupuestos;
    }
}
