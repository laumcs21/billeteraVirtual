package uniquindio.edu.poo.billetera_model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiaRespaldo implements Runnable {
    private static final String ORIGEN = "C:\\td\\persistencia\\archivos\\transacciones.txt";
    private static final String DESTINO_CARPETA = "C:\\td\\persistencia\\respaldo\\";
    private volatile boolean running = true;

    public void copiarArchivo() {
        String nombreArchivoDestino = DESTINO_CARPETA + "copia_transacciones" + System.currentTimeMillis() + ".txt";
        try (FileInputStream fis = new FileInputStream(ORIGEN);
                FileOutputStream fos = new FileOutputStream(nombreArchivoDestino)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            System.out.println("Copia guardada en: " + nombreArchivoDestino);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) {
            copiarArchivo();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void detener() {
        running = false;
    }
}