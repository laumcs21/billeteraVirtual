package uniquindio.edu.poo.billetera_model;

import java.util.Optional;

import uniquindio.edu.poo.billetera_archivo_util.ArchivoUtil;
import uniquindio.edu.poo.billetera_persistencia.Persistencia_Cuenta;
import java.util.List;

public class CuentaCRUD implements CRUD<Cuenta> {
    private Billetera_virtual billetera;
    private Persistencia_Cuenta persistencia = new Persistencia_Cuenta();

    public CuentaCRUD(Billetera_virtual billetera) {
        this.billetera = billetera;
    }

    public Optional<Cuenta> buscarCuentaPorId(String identificacion) {
        return buscarCuentaRecursivo(billetera.getCuentas(), identificacion, 0);
    }

    private Optional<Cuenta> buscarCuentaRecursivo(List<Cuenta> cuentas, String identificacion, int indice) {
        if (indice >= cuentas.size()) {
            return Optional.empty();
        }

        Cuenta cuenta = cuentas.get(indice);
        if (cuenta.getId().equals(identificacion)) {
            return Optional.of(cuenta);
        }

        return buscarCuentaRecursivo(cuentas, identificacion, indice + 1);
    }

    public static boolean buscarCadena(String frase, String busqueda) {
        return frase.contains(busqueda);
    }

    @Override
    public void actualizar(Cuenta cuenta) {
        eliminar(cuenta.getId());
        billetera.getCuentas().add(cuenta);
        persistencia.guardarTodasLasCuentas(billetera.getCuentas());
        ArchivoUtil.guardarRegistroLog(" Cuenta Actualizada--> "+ " ID Usuario:" + cuenta.getIdUsuario()+ " - ID: " + cuenta.getId() + " - Nombre Banco: "+ cuenta.getNombreBanco() + " - Número Cuenta: "+ cuenta.getNumeroCuenta() + " - Tipo de Cuenta: "+ cuenta.getTipoCuenta() + " - Saldo: "+ cuenta.getSaldo(), 1, " btnActualizarCuenta", "C:\\td\\persistencia\\log\\logCrudCuenta.txt");
    }

    @Override
    public Cuenta crear(Cuenta cuenta) {
        if (buscarCuentaPorId(cuenta.getId()).isPresent()) {
            throw new IllegalArgumentException("La cuenta ya está creada.");
        }
        billetera.getCuentas().add(cuenta);
        persistencia.guardarTodasLasCuentas(billetera.getCuentas());

        ArchivoUtil.guardarRegistroLog(" Cuenta Creada--> "+ " ID Usuario:" + cuenta.getIdUsuario()+ " - ID: " + cuenta.getId() + " - Nombre Banco: "+ cuenta.getNombreBanco() + " - Número Cuenta: "+ cuenta.getNumeroCuenta() + " - Tipo de Cuenta: "+ cuenta.getTipoCuenta() + " - Saldo: "+ cuenta.getSaldo(), 1, " btnCrearCuenta", "C:\\td\\persistencia\\log\\logCrudCuenta.txt");

        return cuenta;
    }

    @Override
    public void eliminar(String identificacion) {
        Cuenta cuenta = leer(identificacion);
        billetera.getCuentas().remove(cuenta);
        persistencia.guardarTodasLasCuentas(billetera.getCuentas());
        ArchivoUtil.guardarRegistroLog(" Cuenta Eliminada--> "+ " ID Usuario:" + cuenta.getIdUsuario()+ " - ID: " + cuenta.getId() + " - Nombre Banco: "+ cuenta.getNombreBanco() + " - Número Cuenta: "+ cuenta.getNumeroCuenta() + " - Tipo de Cuenta: "+ cuenta.getTipoCuenta() + " - Saldo: "+ cuenta.getSaldo(), 1, " btnEliminarCuenta", "C:\\td\\persistencia\\log\\logCrudCuenta.txt");
    }

    @Override
    public Cuenta leer(String identificacion) {
        return buscarCuentaPorId(identificacion)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta no está registrado."));
    }
}
