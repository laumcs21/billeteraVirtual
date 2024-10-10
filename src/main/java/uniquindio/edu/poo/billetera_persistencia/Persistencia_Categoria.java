package uniquindio.edu.poo.billetera_persistencia;

import java.util.List;
import uniquindio.edu.poo.billetera_model.Categoria;
import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia_Categoria {

    private static final String RUTA_ARCHIVO = "C:\\td\\persistencia\\archivos\\categorias.txt";
    private static Persistencia_Categoria instancia;

    public static Persistencia_Categoria getInstancia() {
        if (instancia == null) {
            synchronized (Persistencia_Categoria.class) {
                if (instancia == null) {
                    instancia = new Persistencia_Categoria();
                }
            }
        }
        return instancia;
    }

    public void guardarTodasLasCategorias(List<Categoria> categorias) {
        StringBuilder textoCategoria = new StringBuilder();

        for (Categoria categoria : categorias) {
            textoCategoria.append(categoria.getId()).append("@@");
            textoCategoria.append(categoria.getNombre()).append("@@");
            textoCategoria.append(categoria.getDescripcion()).append("\n");
        }

        try {
            ArchivoUtil.guardarArchivo(RUTA_ARCHIVO, textoCategoria.toString(), false);
        } catch (IOException e) {
            System.err.println("Error al guardar las categorías: " + e.getMessage());
        }
    }

    public List<Categoria> cargarCategorias() throws IOException {
        List<Categoria> categorias = new ArrayList<>();

        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO);

        try {

            for (String categoriaTexto : contenido) {
                String[] split = categoriaTexto.split("@@");
                if (split.length == 2) {
                    Categoria categoria = new Categoria.Builder(split[0], split[1]).build();
                    categorias.add(categoria);
                } else {
                    Categoria categoria = new Categoria.Builder(split[0], split[1]).conDescripcion(split[2]).build();
                    categorias.add(categoria);
                }
            }

        } catch (Exception e) {
            System.err.println("Error al cargar los usuarios desde el archivo: " + e.getMessage());
        }
        return categorias;
    }
}
