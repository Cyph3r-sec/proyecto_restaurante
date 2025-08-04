package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Producto;
import modelo.Pedido;
import cliente.ClienteRestaurante;

public class InterfazRestaurante extends JFrame {
    private JTextArea areaPedidos;
    private JComboBox<Producto> comboProductos;
    private JTextField txtCantidad;
    private JButton btnAgregarPedido;
    private JButton btnEnviarPedido;
    private ClienteRestaurante cliente;

    public InterfazRestaurante(ClienteRestaurante cliente) {
        this.cliente = cliente;
        setTitle("Sistema de Gestión de Pedidos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

  
        areaPedidos = new JTextArea();
        areaPedidos.setEditable(false);
        add(new JScrollPane(areaPedidos), BorderLayout.CENTER);

        
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new FlowLayout());

        comboProductos = new JComboBox<>();
        cargarMenu();
        panelControl.add(comboProductos);

    
        txtCantidad = new JTextField(5);
        panelControl.add(txtCantidad);

        
        btnAgregarPedido = new JButton("Agregar Pedido");
        btnAgregarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPedido();
            }
        });
        panelControl.add(btnAgregarPedido);

       
        btnEnviarPedido = new JButton("Enviar Pedido");
        btnEnviarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarPedido();
            }
        });
        panelControl.add(btnEnviarPedido);

        add(panelControl, BorderLayout.SOUTH);
    }

    private void cargarMenu() {
        
        List<Producto> productos = cliente.obtenerMenu();
        for (Producto producto : productos) {
            comboProductos.addItem(producto);
        }
    }

    private void agregarPedido() {
        Producto producto = (Producto) comboProductos.getSelectedItem();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        areaPedidos.append("Producto: " + producto.getNombre() + ", Cantidad: " + cantidad + "\n");
    }

    private void enviarPedido() {
        
        String pedido = areaPedidos.getText();
        cliente.enviarPedido(pedido);
        areaPedidos.setText(""); 
    }

    public static void main(String[] args) {
        ClienteRestaurante cliente = new ClienteRestaurante();
        InterfazRestaurante interfaz = new InterfazRestaurante(cliente);
        interfaz.setVisible(true);
    }
}
