package uniquindio.edu.poo.mapping.mappers;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import uniquindio.edu.poo.billetera_model.Categoria;
import uniquindio.edu.poo.billetera_model.Cuenta;
import uniquindio.edu.poo.billetera_model.Presupuesto;
import uniquindio.edu.poo.billetera_model.TipoCuenta;
import uniquindio.edu.poo.billetera_model.TipoTransaccion;
import uniquindio.edu.poo.billetera_model.Transaccion;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.mapping.dto.CategoriaDto;
import uniquindio.edu.poo.mapping.dto.CuentaDto;
import uniquindio.edu.poo.mapping.dto.PresupuestoDto;
import uniquindio.edu.poo.mapping.dto.TransaccionDto;
import uniquindio.edu.poo.mapping.dto.UsuarioDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-17T22:41:26-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
public class BancoMapperImpl implements BancoMapper {

    @Override
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String id = null;
        String contraseña = null;
        String nombre = null;
        String correo = null;
        String telefono = null;
        String direccion = null;
        double saldoTotal = 0.0d;

        id = usuario.getId();
        contraseña = usuario.getContraseña();
        nombre = usuario.getNombre();
        correo = usuario.getCorreo();
        telefono = usuario.getTelefono();
        direccion = usuario.getDireccion();
        saldoTotal = usuario.getSaldoTotal();

        UsuarioDto usuarioDto = new UsuarioDto( id, contraseña, nombre, correo, telefono, direccion, saldoTotal );

        return usuarioDto;
    }

    @Override
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        if ( usuarioDto == null ) {
            return null;
        }

        String contraseña = null;
        String correo = null;
        String direccion = null;
        String id = null;
        String nombre = null;
        String telefono = null;
        double saldoTotal = 0.0d;

        contraseña = usuarioDto.contraseña();
        correo = usuarioDto.correo();
        direccion = usuarioDto.direccion();
        id = usuarioDto.id();
        nombre = usuarioDto.nombre();
        telefono = usuarioDto.telefono();
        saldoTotal = usuarioDto.saldoTotal();

        Usuario usuario = new Usuario( id, contraseña, nombre, correo, telefono, direccion, saldoTotal );

        return usuario;
    }

    @Override
    public TransaccionDto transaccionToTransaccionDto(Transaccion transaccion) {
        if ( transaccion == null ) {
            return null;
        }

        String idUsuario = null;
        String id = null;
        LocalDate fecha = null;
        TipoTransaccion tipo = null;
        double monto = 0.0d;
        String descripcion = null;
        String numeroCuentaOrigen = null;
        String numeroCuentaDestino = null;
        String idCategoria = null;

        idUsuario = transaccion.getIdUsuario();
        id = transaccion.getId();
        fecha = transaccion.getFecha();
        tipo = transaccion.getTipo();
        monto = transaccion.getMonto();
        descripcion = transaccion.getDescripcion();
        numeroCuentaOrigen = transaccion.getNumeroCuentaOrigen();
        numeroCuentaDestino = transaccion.getNumeroCuentaDestino();
        idCategoria = transaccion.getIdCategoria();

        TransaccionDto transaccionDto = new TransaccionDto( idUsuario, id, fecha, tipo, monto, descripcion, numeroCuentaOrigen, numeroCuentaDestino, idCategoria );

        return transaccionDto;
    }

    @Override
    public Transaccion transaccionDtoToTransaccion(TransaccionDto transaccionDto) {
        if ( transaccionDto == null ) {
            return null;
        }

        Transaccion.Builder builder = null;

        Transaccion transaccion = new Transaccion( builder );

        transaccion.setDescripcion( transaccionDto.descripcion() );
        transaccion.setFecha( transaccionDto.fecha() );
        transaccion.setId( transaccionDto.id() );
        transaccion.setIdCategoria( transaccionDto.idCategoria() );
        transaccion.setIdUsuario( transaccionDto.idUsuario() );
        transaccion.setMonto( transaccionDto.monto() );
        transaccion.setNumeroCuentaDestino( transaccionDto.numeroCuentaDestino() );
        transaccion.setNumeroCuentaOrigen( transaccionDto.numeroCuentaOrigen() );
        transaccion.setTipo( transaccionDto.tipo() );

        return transaccion;
    }

    @Override
    public PresupuestoDto presupuestoToPresupuestoDto(Presupuesto presupuesto) {
        if ( presupuesto == null ) {
            return null;
        }

        String idUsuario = null;
        String id = null;
        String nombre = null;
        double monto = 0.0d;
        String idCategoria = null;
        double montoGastado = 0.0d;

        idUsuario = presupuesto.getIdUsuario();
        id = presupuesto.getId();
        nombre = presupuesto.getNombre();
        monto = presupuesto.getMonto();
        idCategoria = presupuesto.getIdCategoria();
        montoGastado = presupuesto.getMontoGastado();

        PresupuestoDto presupuestoDto = new PresupuestoDto( idUsuario, id, nombre, monto, idCategoria, montoGastado );

        return presupuestoDto;
    }

    @Override
    public Presupuesto presupuestoDtoToPresupuesto(PresupuestoDto presupuestoDto) {
        if ( presupuestoDto == null ) {
            return null;
        }

        String id = null;
        String idCategoria = null;
        String idUsuario = null;
        double monto = 0.0d;
        String nombre = null;

        id = presupuestoDto.id();
        idCategoria = presupuestoDto.idCategoria();
        idUsuario = presupuestoDto.idUsuario();
        monto = presupuestoDto.monto();
        nombre = presupuestoDto.nombre();

        Presupuesto presupuesto = new Presupuesto( idUsuario, id, nombre, monto, idCategoria );

        presupuesto.setMontoGastado( presupuestoDto.montoGastado() );

        return presupuesto;
    }

    @Override
    public CuentaDto cuentaToCuentaDto(Cuenta cuenta) {
        if ( cuenta == null ) {
            return null;
        }

        String idUsuario = null;
        String id = null;
        String nombreBanco = null;
        String numeroCuenta = null;
        TipoCuenta tipoCuenta = null;
        double saldo = 0.0d;

        idUsuario = cuenta.getIdUsuario();
        id = cuenta.getId();
        nombreBanco = cuenta.getNombreBanco();
        numeroCuenta = cuenta.getNumeroCuenta();
        tipoCuenta = cuenta.getTipoCuenta();
        saldo = cuenta.getSaldo();

        CuentaDto cuentaDto = new CuentaDto( idUsuario, id, nombreBanco, numeroCuenta, tipoCuenta, saldo );

        return cuentaDto;
    }

    @Override
    public Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto) {
        if ( cuentaDto == null ) {
            return null;
        }

        String id = null;
        String idUsuario = null;
        String nombreBanco = null;
        String numeroCuenta = null;
        double saldo = 0.0d;
        TipoCuenta tipoCuenta = null;

        id = cuentaDto.id();
        idUsuario = cuentaDto.idUsuario();
        nombreBanco = cuentaDto.nombreBanco();
        numeroCuenta = cuentaDto.numeroCuenta();
        saldo = cuentaDto.saldo();
        tipoCuenta = cuentaDto.tipoCuenta();

        Cuenta cuenta = new Cuenta( idUsuario, id, nombreBanco, numeroCuenta, tipoCuenta, saldo );

        return cuenta;
    }

    @Override
    public CategoriaDto categoriaToCategoriaDto(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        String id = null;
        String nombre = null;
        String descripcion = null;

        id = categoria.getId();
        nombre = categoria.getNombre();
        descripcion = categoria.getDescripcion();

        CategoriaDto categoriaDto = new CategoriaDto( id, nombre, descripcion );

        return categoriaDto;
    }

    @Override
    public Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto) {
        if ( categoriaDto == null ) {
            return null;
        }

        Categoria.Builder builder = null;

        Categoria categoria = new Categoria( builder );

        categoria.setDescripcion( categoriaDto.descripcion() );
        categoria.setId( categoriaDto.id() );
        categoria.setNombre( categoriaDto.nombre() );

        return categoria;
    }
}
