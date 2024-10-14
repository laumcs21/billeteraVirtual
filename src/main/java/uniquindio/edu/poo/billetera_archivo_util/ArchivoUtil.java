package uniquindio.edu.poo.billetera_archivo_util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import java.io.File;


public class ArchivoUtil {

    public static void guardarArchivo(String ruta, String contenido, Boolean flagAnexarContenido) throws IOException {

        File archivo = new File(ruta);
        File directorio = archivo.getParentFile();

        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }

        if (!archivo.exists()) {
            archivo.createNewFile();
        }

        FileWriter fw = new FileWriter(ruta, flagAnexarContenido);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }

    public static ArrayList<String> leerArchivo(String ruta) throws IOException {

        ArrayList<String> contenido = new ArrayList<String>();
        FileReader fr = new FileReader(ruta);
        BufferedReader bfr = new BufferedReader(fr);
        String linea = "";
        while ((linea = bfr.readLine()) != null) {
            contenido.add(linea);
        }
        bfr.close();
        fr.close();
        return contenido;
    }

    public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo) {
		Logger LOGGER = Logger.getLogger(accion);
		FileHandler fileHandler = null;
	
		try {
			// Crear el FileHandler con la ruta del archivo
			fileHandler = new FileHandler(rutaArchivo, true);
			fileHandler.setFormatter(new SimpleFormatter());
	
			// Añadir el manejador al Logger
			LOGGER.addHandler(fileHandler);
	
			// Dependiendo del nivel, registrar el log adecuado
			switch (nivel) {
				case 1:
					LOGGER.log(Level.INFO, accion + "," + mensajeLog);
					break;
				case 2:
					LOGGER.log(Level.WARNING, accion + "," + mensajeLog);
					break;
				case 3:
					LOGGER.log(Level.SEVERE, accion + "," + mensajeLog);
					break;
				default:
					break;
			}
	
		} catch (SecurityException | IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		} finally {
			// Cerrar y eliminar el FileHandler para evitar duplicados
			if (fileHandler != null) {
				fileHandler.close();  // Cerrar el archivo
				LOGGER.removeHandler(fileHandler);  // Remover el handler para evitar acumulación
			}
		}
	}
	
}
