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
<<<<<<< HEAD
    public String contraseña;

    public Persona(String id, String contraseña, String nombre, String correo, String telefono, String direccion) {
=======

    public Persona(String id, String nombre, String correo, String telefono, String direccion) {
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
<<<<<<< HEAD
        this.contraseña = contraseña;
=======
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c

    }

}
