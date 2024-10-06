package uniquindio.edu.poo.billetera_archivo_util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class archivoUtilUsuario {

    private static final String RUTA_ARCHIVO = "src/data/usuarios.txt";

    public static void guardarTexto(String contenido) throws IOException {
        File directorio = new File("src/data");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                throw new IOException("No se pudo crear el directorio: " + directorio.getAbsolutePath());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            writer.write(contenido);
            System.out.println("Archivo guardado correctamente en: " + RUTA_ARCHIVO);
        } catch (IOException e) {
            throw new IOException("Error al guardar el archivo en " + RUTA_ARCHIVO + ": " + e.getMessage(), e);
        }
    }
}
