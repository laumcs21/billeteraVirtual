package uniquindio.edu.poo.billetera_model;

public class Sesion {
    private static boolean esAdmin;
    private static String idUsuario;

    public static boolean getEsAdmin() {
        return esAdmin;
    }

    public static void setEsAdmin(boolean esAdmin) {
        Sesion.esAdmin = esAdmin;
    }

    public static String getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(String idUsuario) {
        Sesion.idUsuario = idUsuario;
    }
}
