package uniquindio.edu.poo.billetera_model;

import java.util.Optional;

import uniquindio.edu.poo.billetera_persistencia.Persistencia_Categoria;
import java.util.List;

public class CategoriaCRUD implements CRUD<Categoria> {

    private Billetera_virtual billetera;
    private Persistencia_Categoria persistencia = new Persistencia_Categoria();

    public CategoriaCRUD(Billetera_virtual billetera) {
        this.billetera = billetera;
    }

    public Optional<Categoria> buscarCategoriaPorIdentificacion(String identificacion) {
        return buscarCategoriaRecursivo(billetera.getCategorias(), identificacion, 0);
    }

    private Optional<Categoria> buscarCategoriaRecursivo(List<Categoria> categorias, String identificacion,
            int indice) {
        if (indice >= categorias.size()) {
            return Optional.empty();
        }

        Categoria categoria = categorias.get(indice);
        if (categoria.getId().equals(identificacion)) {
            return Optional.of(categoria);
        }

        return buscarCategoriaRecursivo(categorias, identificacion, indice + 1);
    }

    @Override
    public void actualizar(Categoria categoria) {
        eliminar(categoria.getId());
        billetera.getCategorias().add(categoria);
        persistencia.guardarTodasLasCategorias(billetera.getCategorias());
    }

    @Override
    public Categoria crear(Categoria categoria) {
        if (buscarCategoriaPorIdentificacion(categoria.getId()).isPresent()) {
            throw new IllegalArgumentException("La categoría ya está registrada.");
        }
        billetera.getCategorias().add(categoria);
        persistencia.guardarTodasLasCategorias(billetera.getCategorias());

        return categoria;
    }

    @Override
    public void eliminar(String identificacion) {
        Categoria categoria = leer(identificacion);
        billetera.getCategorias().remove(categoria);
        persistencia.guardarTodasLasCategorias(billetera.getCategorias());
    }

    @Override
    public Categoria leer(String identificacion) {
        return buscarCategoriaPorIdentificacion(identificacion)
                .orElseThrow(() -> new IllegalArgumentException("La categoría no está registrado."));
    }
}
