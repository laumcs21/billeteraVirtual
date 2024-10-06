package uniquindio.edu.poo.billetera_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Administrador extends Persona {

    private static Administrador instancia;
<<<<<<< HEAD

    private Administrador(String id, String contraseña, String nombre, String correo, String telefono,
            String direccion) {
        super(id, contraseña, nombre, correo, telefono, direccion);
=======
    public String contraseña;

    private Administrador(String id, String contraseña, String nombre, String correo, String telefono,
            String direccion) {
        super(id, nombre, correo, telefono, direccion);
>>>>>>> a21a333b0d6430c7dcb68350ba641dc77907185c

        this.contraseña = contraseña;
    }

    public static Administrador getInstance() {
        if (instancia == null) {
            instancia = new Administrador(
                    "1001198723",
                    "1111",
                    "Laura Milena Cardenas",
                    "Laumcs21@gmail.com",
                    "3003710163",
                    "Villa carolina primera etapa mz.M #12");
        }
        return instancia;
    }
}
