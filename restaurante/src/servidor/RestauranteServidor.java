package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestauranteServidor {
    private static final int PUERTO = 12345;
    private static final int MAX_CONEXIONES = 50;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_CONEXIONES);

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor del restaurante iniciado. Esperando conexiones...");

            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + socketCliente.getInetAddress());
                pool.execute(new ManejadorCliente(socketCliente));
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }
}
