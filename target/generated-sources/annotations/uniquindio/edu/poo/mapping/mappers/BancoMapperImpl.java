package uniquindio.edu.poo.mapping.mappers;

import uniquindio.edu.poo.billetera_model.Categoria;
import uniquindio.edu.poo.billetera_model.Cuenta;
import uniquindio.edu.poo.billetera_model.Presupuesto;
import uniquindio.edu.poo.billetera_model.Transaccion;
import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.mapping.dto.CategoriaDto;
import uniquindio.edu.poo.mapping.dto.CuentaDto;
import uniquindio.edu.poo.mapping.dto.PresupuestoDto;
import uniquindio.edu.poo.mapping.dto.TransaccionDto;
import uniquindio.edu.poo.mapping.dto.UsuarioDto;

public class BancoMapperImpl implements BancoMapper {

    @Override
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDto(
                usuario.getId(),
                usuario.getContraseña(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getSaldoTotal());
    }

    @Override
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        if (usuarioDto == null) {
            return null;
        }
        return new Usuario(
                usuarioDto.id(),
                usuarioDto.contraseña(),
                usuarioDto.nombre(),
                usuarioDto.correo(),
                usuarioDto.telefono(),
                usuarioDto.direccion(),
                usuarioDto.saldoTotal());
    }

    @Override
    public TransaccionDto transaccionToTransaccionDto(Transaccion transaccion) {
        if (transaccion == null) {
            return null;
        }
        return new TransaccionDto(
                transaccion.getIdUsuario(),
                transaccion.getId(),
                transaccion.getFecha(),
                transaccion.getTipo(),
                transaccion.getMonto(),
                transaccion.getDescripcion(),
                transaccion.getNumeroCuentaOrigen(),
                transaccion.getNumeroCuentaDestino(),
                transaccion.getIdCategoria());
    }

    @Override
    public Transaccion transaccionDtoToTransaccion(TransaccionDto transaccionDto) {
        if (transaccionDto == null) {
            return null;
        }
        Transaccion.Builder builder = new Transaccion.Builder(
                transaccionDto.idUsuario(),
                transaccionDto.id(),
                transaccionDto.fecha(),
                transaccionDto.tipo(),
                transaccionDto.monto(),
                transaccionDto.numeroCuentaOrigen());

        if (transaccionDto.numeroCuentaDestino() != null) {
            builder.conNumeroCuentaDestino(transaccionDto.numeroCuentaDestino());
        }
        if (transaccionDto.descripcion() != null) {
            builder.conDescripcion(transaccionDto.descripcion());
        }
        if (transaccionDto.idCategoria() != null) {
            builder.conCategoria(transaccionDto.idCategoria());
        }

        return builder.build();
    }

    @Override
    public PresupuestoDto presupuestoToPresupuestoDto(Presupuesto presupuesto) {
        if (presupuesto == null) {
            return null;
        }
        return new PresupuestoDto(
                presupuesto.getIdUsuario(),
                presupuesto.getId(),
                presupuesto.getNombre(),
                presupuesto.getMonto(),
                presupuesto.getIdCategoria(),
                presupuesto.getMontoGastado());
    }

    @Override
    public Presupuesto presupuestoDtoToPresupuesto(PresupuestoDto presupuestoDto) {
        if (presupuestoDto == null) {
            return null;
        }
        Presupuesto presupuesto = new Presupuesto(
                presupuestoDto.idUsuario(),
                presupuestoDto.id(),
                presupuestoDto.nombre(),
                presupuestoDto.monto(),
                presupuestoDto.idCategoria());

        presupuesto.setMontoGastado(presupuestoDto.montoGastado());
        return presupuesto;
    }

    @Override
    public CuentaDto cuentaToCuentaDto(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }
        return new CuentaDto(
                cuenta.getIdUsuario(),
                cuenta.getId(),
                cuenta.getNombreBanco(),
                cuenta.getNumeroCuenta(),
                cuenta.getTipoCuenta(),
                cuenta.getSaldo());
    }

    @Override
    public Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto) {
        if (cuentaDto == null) {
            return null;
        }
        return new Cuenta(
                cuentaDto.idUsuario(),
                cuentaDto.id(),
                cuentaDto.nombreBanco(),
                cuentaDto.numeroCuenta(),
                cuentaDto.tipoCuenta(),
                cuentaDto.saldo());
    }

    @Override
    public CategoriaDto categoriaToCategoriaDto(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return new CategoriaDto(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion());
    }

    @Override
    public Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto) {
        if (categoriaDto == null) {
            return null;
        }
        Categoria.Builder builder = new Categoria.Builder(
                categoriaDto.id(),
                categoriaDto.nombre());

        if (categoriaDto.descripcion() != null) {
            builder.conDescripcion(categoriaDto.descripcion());
        }

        return builder.build();
    }
}