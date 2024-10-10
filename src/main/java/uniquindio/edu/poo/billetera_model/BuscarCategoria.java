package uniquindio.edu.poo.billetera_model;

import java.util.List;

public class BuscarCategoria {

    private static Billetera_virtual billeteraVirtual;

    static {
        billeteraVirtual = Billetera_virtual.getInstancia();
    }

    public static Categoria buscarCategoriaPorID(String id) {
        return buscarCategoriaPorID(billeteraVirtual.getCategorias(), id, 0);
    }

    private static Categoria buscarCategoriaPorID(List<Categoria> categorias, String id, int indice) {
        if (indice >= categorias.size()) {
            return null;
        }

        Categoria categoria = categorias.get(indice);
        if (categoria.getId().equals(id)) {
            return categoria;
        }

        return buscarCategoriaPorID(categorias, id, indice + 1);
    }

}
