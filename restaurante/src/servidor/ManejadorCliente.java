package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private final Socket socketCliente;
    private BufferedReader entrada;
    private PrintWriter salida;

    public ManejadorCliente(Socket socket) {
        this.socketCliente = socket;
    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salida = new PrintWriter(socketCliente.getOutputStream(), true);

            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);
                String respuesta = procesarComando(mensaje);
                salida.println(respuesta);
            }
        } catch (IOException e) {
            System.err.println("Error con cliente: " + e.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    private String procesarComando(String mensaje) {

        if (mensaje.startsWith("NUEVO_PEDIDO")) {
  
            return "OK|Pedido recibido";
        } else if (mensaje.equals("OBTENER_MENU")) {

            return "OK|Producto1|10.0|Producto2|15.0|"; 
        }
        return "ERROR|Comando no reconocido";
    }

    private void cerrarConexion() {
        try {
            if (entrada != null) entrada.close();
            if (salida != null) salida.close();
            if (socketCliente != null) socketCliente.close();
        } catch (IOException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
