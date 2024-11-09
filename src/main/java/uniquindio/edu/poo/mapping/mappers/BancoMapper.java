package uniquindio.edu.poo.mapping.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uniquindio.edu.poo.billetera_model.Usuario;
import uniquindio.edu.poo.billetera_model.Transaccion;
import uniquindio.edu.poo.billetera_model.Presupuesto;
import uniquindio.edu.poo.billetera_model.Cuenta;
import uniquindio.edu.poo.billetera_model.Categoria;

import uniquindio.edu.poo.mapping.dto.UsuarioDto;
import uniquindio.edu.poo.mapping.dto.TransaccionDto;
import uniquindio.edu.poo.mapping.dto.PresupuestoDto;
import uniquindio.edu.poo.mapping.dto.CuentaDto;
import uniquindio.edu.poo.mapping.dto.CategoriaDto;

@Mapper(componentModel = "spring")
public interface BancoMapper {
    BancoMapper INSTANCE = Mappers.getMapper(BancoMapper.class);

    // Mapping Usuario -> UsuarioDto
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    // Mapping Transaccion -> TransaccionDto
    @Mapping(target = "tipo", source = "tipo")
    TransaccionDto transaccionToTransaccionDto(Transaccion transaccion);

    Transaccion transaccionDtoToTransaccion(TransaccionDto transaccionDto);

    // Mapping Presupuesto -> PresupuestoDto
    PresupuestoDto presupuestoToPresupuestoDto(Presupuesto presupuesto);

    Presupuesto presupuestoDtoToPresupuesto(PresupuestoDto presupuestoDto);

    // Mapping Cuenta -> CuentaDto
    @Mapping(target = "tipoCuenta", source = "tipoCuenta")
    CuentaDto cuentaToCuentaDto(Cuenta cuenta);

    Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto);

    // Mapping Categoria -> CategoriaDto
    CategoriaDto categoriaToCategoriaDto(Categoria categoria);

    Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto);
}
