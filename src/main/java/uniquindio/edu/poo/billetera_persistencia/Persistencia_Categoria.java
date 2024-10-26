package uniquindio.edu.poo.billetera_persistencia;

import java.util.List;
import uniquindio.edu.poo.billetera_model.Categoria;
import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia_Categoria {

    private static final String RUTA_ARCHIVO = "C:\\td\\persistencia\\archivos\\categorias.txt";
    private static final String RUTA_ARCHIVOXML = "C:\\td\\persistencia\\categorias.data";
    private static final String RUTA_ARCHIVOBIN = "C:\\td\\persistencia\\categoriasBinario.data\\";
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
    public List<Categoria> cargarCategoriasXML() {
        try {
            return (List<Categoria>) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVOXML);
        } catch (IOException e) {
            System.err.println("Error al cargar las categorias desde el archivo XML: " + e.getMessage());
            return new ArrayList<>();
        } catch (ClassCastException e) {
            System.err.println(
                    "Error de conversión al cargar las categorias desde el archivo XML: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void guardarCategoriaEnXML(Categoria categoria) {
        try {
            List<Categoria> categorias = cargarCategoriasXML();

            if (categorias == null) {
                categorias = new ArrayList<>();
            }

            categorias.add(categoria);

            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVOXML, categorias);
        } catch (IOException e) {
            System.out.println("Error al guardar la categoria: " + e.getMessage());
        }
    }

    public void guardarCategoriaBinario(Categoria categoria) {
        try {


  
            List<Categoria> categorias = cargarCategoriasBinario();

            if (categorias == null) {
                categorias = new ArrayList<>();
            }

            categorias.add(categoria);

            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVOBIN, categorias);
            System.out.println("Registro guardado en binario: " + categoria);

        } catch (Exception e) {
            System.out.println("Error al guardar las categorias en binario: " + e.getMessage());
        }
    }

    private List<Categoria> cargarCategoriasBinario() {
        try {
            return (List<Categoria>) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVOBIN);
        } catch (Exception e) {
            System.out.println("Error al cargar las categorias desde binario: " + e.getMessage());
            return null;
        }
    }
}

