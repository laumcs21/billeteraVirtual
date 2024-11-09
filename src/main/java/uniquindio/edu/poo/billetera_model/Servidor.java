package uniquindio.edu.poo.billetera_model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class Servidor extends JFrame implements Runnable {

    private JTextArea areaTexto;
    private ServerSocket server;
    private Billetera_virtual billetera_virtual;

    public Servidor() {
        this.billetera_virtual = Billetera_virtual.getInstancia();
        setBounds(1200, 300, 400, 300);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);

        JPanel milamina = new JPanel(new BorderLayout());
        milamina.add(scroll, BorderLayout.CENTER);
        add(milamina);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Iniciando servidor...");
            server = new ServerSocket(9091);

            while (true) {
                System.out.println("Esperando conexión del cliente...");
                Socket socket = server.accept();
                System.out.println("Cliente conectado.");

                new Thread(() -> procesarCliente(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void procesarCliente(Socket socket) {
        try (DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
                DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream())) {

            String identificacion = flujoEntrada.readUTF();
            String contraseña = flujoEntrada.readUTF();

            areaTexto.append("\nIdentificación: " + identificacion);
            areaTexto.append("\nContraseña: " + contraseña);
            areaTexto.append("\n---------------------------");

            // Verificar si la identificación y la contraseña corresponden al administrador
            Administrador administrador = Administrador.getInstance();
            if (administrador.getId().equals(identificacion) && administrador.getContraseña().equals(contraseña)) {
                flujoSalida.writeUTF("ACCESO_CONCEDIDO_ADMINISTRADOR");
                areaTexto.append("\nAcceso concedido: Administrador\n");
                return;
            }

            // Verificar si la identificación y la contraseña corresponden a un usuario
            // regular
            List<Usuario> usuarios = billetera_virtual.getUsuarios();
            for (Usuario usuario : usuarios) {
                if (usuario.getId().equals(identificacion) && usuario.getContraseña().equals(contraseña)) {
                    flujoSalida.writeUTF("ACCESO_CONCEDIDO_USUARIO");
                    areaTexto.append("\nAcceso concedido: Usuario\n");
                    return;
                }
            }

            // Verificar si la identificación existe para un usuario regular o el
            // administrador
            boolean usuarioExiste = usuarios.stream().anyMatch(u -> u.getId().equals(identificacion));
            boolean adminExiste = administrador.getId().equals(identificacion);

            if (usuarioExiste || adminExiste) {
                flujoSalida.writeUTF("ACCESO_DENEGADO");
                areaTexto.append("\nAcceso denegado: Contraseña incorrecta\n");
            } else {
                // Si la identificación no se encuentra ni como usuario ni como administrador
                flujoSalida.writeUTF("USUARIO_NO_REGISTRADO");
                areaTexto.append("\nAcceso denegado: Usuario no registrado\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
