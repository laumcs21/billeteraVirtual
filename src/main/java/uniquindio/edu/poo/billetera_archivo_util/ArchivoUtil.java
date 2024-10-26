package uniquindio.edu.poo.billetera_archivo_util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.FileInputStream;





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

    public static Object cargarRecursoSerializado(String rutaArchivo) throws Exception {
        Object aux = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(rutaArchivo));

            aux = ois.readObject();

        } catch (Exception e2) {
            throw e2;
        } finally {
            if (ois != null)
                ois.close();
        }
        return aux;
    }

    public static void salvarRecursoSerializado(String rutaArchivo, Object object) throws Exception {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            oos.writeObject(object);
        } catch (Exception e) {
            throw e;
        } finally {
            if (oos != null)
                oos.close();
        }
    }

    public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {

        XMLDecoder decodificadorXML;
        Object objetoXML;

        decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
        objetoXML = decodificadorXML.readObject();
        decodificadorXML.close();
        return objetoXML;

    }

    public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException {

        XMLEncoder codificadorXML;

        codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
        codificadorXML.writeObject(objeto);
        codificadorXML.close();

    }
}
