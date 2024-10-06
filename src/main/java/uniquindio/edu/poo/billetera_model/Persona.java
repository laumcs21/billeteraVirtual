package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Persona {

    public String id;
    public String nombre;
    public String correo;
    public String telefono;
    public String direccion;
    public String contraseña;

    public Persona(String id, String contraseña, String nombre, String correo, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.contraseña = contraseña;

    }

}
