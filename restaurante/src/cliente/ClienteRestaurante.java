package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

public class ClienteRestaurante {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClienteRestaurante() {
        try {
            socket = new Socket(HOST, PUERTO);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> obtenerMenu() {
        List<Producto> productos = new ArrayList<>();
        try {
            out.println("OBTENER_MENU");
            String respuesta = in.readLine();

            String[] partes = respuesta.split("\\|");
            for (int i = 1; i < partes.length; i += 2) {
                String nombre = partes[i];
                double precio = Double.parseDouble(partes[i + 1]);
                productos.add(new Producto(nombre, precio));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public void enviarPedido(String pedido) {
        out.println("NUEVO_PEDIDO|" + pedido);
        try {
            String respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
