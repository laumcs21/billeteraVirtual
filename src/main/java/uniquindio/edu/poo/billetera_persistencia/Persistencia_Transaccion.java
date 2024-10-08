package uniquindio.edu.poo.billetera_persistencia;

import java.util.List;

import uniquindio.edu.poo.billetera_model.Transaccion;
import uniquindio.edu.poo.billetera_model.TipoTransaccion;
import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Persistencia_Transaccion {
    private static final String RUTA_ARCHIVO = "C:\\td\\persistencia\\archivos\\transacciones.txt";
    private static Persistencia_Transaccion instancia;

    public static Persistencia_Transaccion getInstancia() {
        if (instancia == null) {
            synchronized (Persistencia_Transaccion.class) {
                if (instancia == null) {
                    instancia = new Persistencia_Transaccion();
                }
            }
        }
        return instancia;
    }

    public void guardarTodasLasTransacciones(List<Transaccion> transacciones) {

        StringBuilder textoTransaccion = new StringBuilder();

        for (Transaccion transaccion : transacciones) {
            textoTransaccion.append(transaccion.getIdUsuario()).append("@@");
            textoTransaccion.append(transaccion.getId()).append("@@");
            textoTransaccion.append(transaccion.getFecha()).append("@@");
            textoTransaccion.append(transaccion.getTipo()).append("@@");
            textoTransaccion.append(transaccion.getMonto()).append("@@");
            textoTransaccion.append(transaccion.getNumeroCuentaOrigen()).append("@@");

            if (transaccion.getNumeroCuentaDestino() != null) {
                textoTransaccion.append("destino=").append(transaccion.getNumeroCuentaDestino()).append("@@");
            }
            if (transaccion.getDescripcion() != null) {
                textoTransaccion.append("descripcion=").append(transaccion.getDescripcion()).append("@@");
            }
            if (transaccion.getIdCategoria() != null) {
                textoTransaccion.append("categoria=").append(transaccion.getIdCategoria()).append("@@");
            }
            textoTransaccion.append("\n");
        }

        try {
            ArchivoUtil.guardarArchivo(RUTA_ARCHIVO, textoTransaccion.toString(), false);
        } catch (IOException e) {
            System.err.println("Error al guardar las transacciones: " + e.getMessage());
        }
    }

    public List<Transaccion> cargarTransacciones() throws IOException {
        List<Transaccion> transacciones = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las transacciones");
        }

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO);

        try {
            for (String transaccionTexto : contenido) {
                String[] split = transaccionTexto.split("@@");
                if (split.length < 6) {
                    System.err.println("Línea inválida o incompleta: " + transaccionTexto);
                    continue;
                }

                String idUsuario = split[0];
                String fechaTexto = split[2];
                String[] fechaPartes = fechaTexto.split("-");
                int año = Integer.parseInt(fechaPartes[0]);
                int mes = Integer.parseInt(fechaPartes[1]);
                int dia = Integer.parseInt(fechaPartes[2]);
                LocalDate fecha = LocalDate.of(año, mes, dia);

                TipoTransaccion tipoTransaccion = TipoTransaccion.valueOf(split[3]);

                Transaccion.Builder transaccionBuilder = new Transaccion.Builder(idUsuario, split[1], fecha,
                        tipoTransaccion, Double.parseDouble(split[4]), split[5]);

                for (int i = 6; i < split.length; i++) {
                    if (split[i].startsWith("destino=")) {
                        String numeroCuentaDestino = split[i].substring("destino=".length());
                        transaccionBuilder.conNumeroCuentaDestino(numeroCuentaDestino);
                    } else if (split[i].startsWith("descripcion=")) {
                        String descripcion = split[i].substring("descripcion=".length());
                        transaccionBuilder.conDescripcion(descripcion);
                    } else if (split[i].startsWith("categoria=")) {
                        String idCategoria = split[i].substring("categoria=".length());
                        transaccionBuilder.conCategoria(idCategoria);
                    }
                }

                Transaccion transaccion = transaccionBuilder.build();
                transacciones.add(transaccion);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar las transacciones desde el archivo: " + e.getMessage());
        }

        return transacciones;
    }
}
